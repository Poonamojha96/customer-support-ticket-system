package com.practice.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class representing user information.
 * Contains details such as user ID, name, password, and role.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERINFO")
public class UserInfo {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The role of the user (e.g., ADMIN, USER).
     */
    private String role;
}
