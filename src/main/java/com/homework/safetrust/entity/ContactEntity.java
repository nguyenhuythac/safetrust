package com.homework.safetrust.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
* 
* Contact entity ORM class.
* 
* @author Thac Nguyen
*/
@Entity(name = "contact")
@Table(name = "contact")
@Data
public class ContactEntity implements Serializable{
    /**
    * Contact entity Id
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
    * Contact entity name
    */
    private String name;

    /**
    * Contact entity email
    */
    private String email;

    /**
    * Contact entity phone
    */
    private String phone;

    /**
    * Contact entity postal address
    */
    private String postal;
}
