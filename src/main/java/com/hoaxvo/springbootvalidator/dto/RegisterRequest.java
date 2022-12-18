package com.hoaxvo.springbootvalidator.dto;

import com.hoaxvo.springbootvalidator.annotations.field.Email;
import com.hoaxvo.springbootvalidator.annotations.field.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(code = "E01", message = "Email not null")
    @Email(code = "E02", message = "Hey email invalid!!!")
    private String email;

}
