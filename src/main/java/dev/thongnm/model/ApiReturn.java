package dev.thongnm.model;

import lombok.Data;

@Data
public class ApiReturn<T> {
    private boolean result;

    private String statusCode;

    private String message;

    private T RespondData;
}
