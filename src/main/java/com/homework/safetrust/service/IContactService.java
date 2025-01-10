package com.homework.safetrust.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.homework.safetrust.entity.ContactEntity;
import com.homework.safetrust.exception.EntityNotFoundException;

/**
* 
* Contact service interface
* @author Thac Nguyen
* 
*/
public interface IContactService {
    /**
    * 
    * <p>Get pagination contact list</p>
    * @param page the offset that the index of first member
    * @param size the quantity of contacts
    * @return Page that is the amount of contacts
    *
    */
    Page<ContactEntity> getContactsList(int page, int size);

    /**
    * 
    * <p>Get all contact list</p>
    * @return the amount of all contacts
    *
    */
    List<ContactEntity> getAllContacts();

    /**
    * 
    * <p>Create new contact</p>
    * @param contactEntity the new contact information
    * @return ContactEntity the new created contact
    *
    */
    ContactEntity createContact(ContactEntity contactEntity);

    /**
    * 
    * <p>Get contact by Id</p>
    * @param id the id that is used to find contact
    * @return ContactEntity the contact that is found
    *
    */
    ContactEntity getContactById(int id) throws EntityNotFoundException;

    /**
    * 
    * <p>Update Contact</p>
    * @param contactEntity the contact information
    * @return ContactEntity the updated contact
    *
    */
    ContactEntity updateContact(ContactEntity contactEntity) throws EntityNotFoundException;

    /**
    * 
    * <p>Delete Contact</p>
    * @param id the id that is deleted
    *
    */
    void deleteContact(int id);

    /**
    * 
    * <p>Find Contacts by first name or last name</p>
    * @param name the name is used to find contacts
    * @return the amount of contacts
    *
    */
    List<ContactEntity> searchByName(String name);
}
