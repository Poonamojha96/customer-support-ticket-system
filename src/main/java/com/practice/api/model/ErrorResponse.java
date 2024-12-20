package com.practice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for error responses.
 * Contains the status code and error message to be returned in error responses.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    /**
     * The HTTP status code of the error response.
     */
    private int statusCode;

    /**
     * The error message describing the error.
     */
    private String errorMessage;
}
