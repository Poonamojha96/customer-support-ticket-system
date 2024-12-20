package com.practice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for authentication requests.
 * Contains the necessary information for user authentication.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    /**
     * The username of the user attempting to authenticate.
     */
    private String username;

    /**
     * The password of the user attempting to authenticate.
     */
    private String password;
}
