package com.homework.safetrust.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
* 
* This class is contact DTO
* 
*/
@Data
public class Contact {
    private int id;
    @NotBlank(message = "Name should not be empty")
    private String name;

    @Email(message = "invalid email address")
    private String email;

    @NotBlank(message = "Phone number should not be empty")
    @Pattern(regexp = "^0\\d{9}$", message = "Wrong phone number format")
    private String phone;

    @NotNull(message = "Postal address should not be null")
    private String postal;
}
