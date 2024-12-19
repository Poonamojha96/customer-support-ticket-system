package com.practice.api.config;

import com.practice.api.filter.JwtAuthFilter;
import com.practice.api.service.impl.UserInfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    //authentication
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("Poonam")
//                .password(encoder.encode("pwd1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user =  User.withUsername("Ojha")
//                .password(encoder.encode("pwd2"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
        return new UserInfoUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(authorize -> authorize
                       .requestMatchers("/v1/tickets/authenticate").permitAll()
                       .requestMatchers("/v1/tickets").permitAll()
                       .requestMatchers("/h2-console/**").permitAll()
                       .requestMatchers("/v1/tickets/**").authenticated())
               .sessionManagement(Customizer. withDefaults())
               .authenticationProvider(authenticationProvider())
               .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
               .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable()) // To allow H2 console within frames
               .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}




