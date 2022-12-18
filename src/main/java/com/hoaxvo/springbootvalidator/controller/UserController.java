package com.hoaxvo.springbootvalidator.controller;

import com.hoaxvo.springbootvalidator.annotations.Validated;
import com.hoaxvo.springbootvalidator.dto.RegisterRequest;
import com.hoaxvo.springbootvalidator.dto.Response;
import com.hoaxvo.springbootvalidator.dto.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @PostMapping(value = "/register")
    @Validated
    public ResponseEntity<Response<RegisterRequest>> registerUser(@RequestBody RegisterRequest request,
                                                                  ValidationError validationError) {
        Response<RegisterRequest> response = new Response<>();

        if (validationError.getCode() != null) {
            log.info("Validation error is {}", validationError);
            response.setError(Response.Error.builder()
                    .code(validationError.getCode())
                    .message(validationError.getMessage())
                    .build());
        }
        log.info("Register request is {}", request);
        response.setData(request);

        return ResponseEntity.ok(response);
    }
}
