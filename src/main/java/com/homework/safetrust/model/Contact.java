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
* @author Thac Nguyen
*/
@Data
public class Contact {
    /**
    * Contact DTO ID
    */
    private int id;

    /**
    * Contact DTO name
    */
    @NotBlank(message = "Name should not be empty")
    private String name;

    /**
    * Contact DTO email
    */
    @Email(message = "invalid email address")
    private String email;

    /**
    * Contact DTO phone
    */
    @NotBlank(message = "Phone number should not be empty")
    @Pattern(regexp = "^0\\d{9}$", message = "Wrong phone number format")
    private String phone;

    /**
    * Contact DTO postal code
    */
    @NotNull(message = "Postal address should not be null")
    private String postal;
}
