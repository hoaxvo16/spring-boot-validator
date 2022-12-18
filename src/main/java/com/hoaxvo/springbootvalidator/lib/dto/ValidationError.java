package com.hoaxvo.springbootvalidator.lib.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError {
    private String code;
    private String message;

    private boolean isPresent;

    public void makeError(String code, String message) {
        this.isPresent = true;
        this.code = code;
        this.message = message;
    }
}
