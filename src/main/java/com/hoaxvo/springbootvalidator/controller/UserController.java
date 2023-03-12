package com.hoaxvo.springbootvalidator.controller;

import com.hoaxvo.springbootvalidator.dto.RegisterRequest;
import com.hoaxvo.springbootvalidator.dto.Response;
import com.hoaxvo.springbootvalidator.lib.annotations.Validated;
import com.hoaxvo.springbootvalidator.lib.annotations.field.Email;
import com.hoaxvo.springbootvalidator.lib.dto.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @PostMapping(value = "/register")
    @Validated
    public ResponseEntity<Response<RegisterRequest>> registerUser(@RequestBody RegisterRequest request,
                                                                  ValidationError validationError) {
        Response<RegisterRequest> response = new Response<>();

        if (validationError.isPresent()) {
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

    @GetMapping(value = "/user")
    @Validated
    ResponseEntity<Response<String>> getUser(@RequestParam(value = "email")
                                             @Email(code = "E02", message = "Hey email invalid!!!")
                                             String email,
                                             ValidationError validationError) {
        Response<String> response = new Response<>();
        if (validationError.isPresent()) {
            log.info("Validation error is {}", validationError);
            response.setError(Response.Error.builder()
                    .code(validationError.getCode())
                    .message(validationError.getMessage())
                    .build());
        }
        log.info("Email is {}", email);

        return ResponseEntity.ok(response);
    }
}
