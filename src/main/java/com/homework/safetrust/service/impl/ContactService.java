package com.homework.safetrust.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.homework.safetrust.controller.ContactController;
import com.homework.safetrust.entity.ContactEntity;
import com.homework.safetrust.exception.EntityNotFoundException;
import com.homework.safetrust.repository.ContactRepository;
import com.homework.safetrust.service.IContactService;

/**
* 
* Contact service class implements IContactService interface
* @author Thac Nguyen
* 
*/
@Service
public class ContactService implements IContactService{

    private Logger logger  = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactRepository contactRepo;

    /**
    * 
    * <p>Get pagination contact list</p>
    * @param page the offset that the index of first member
    * @param size the quantity of contacts
    * @return Page that is the amount of contacts
    *
    */
    @Override
    public Page<ContactEntity> getContactsList(int offset, int pageSize) {
        PageRequest pageReq = PageRequest.of(offset, pageSize);
        Page<ContactEntity> contacts = contactRepo.findAll(pageReq);
        return contacts;
    }

    /**
    * 
    * <p>Get all contact list</p>
    * @return the amount of all contacts
    *
    */
    @Override
    public List<ContactEntity> getAllContacts() {
        return contactRepo.findAll();
    }

    /**
    * 
    * <p>Create new contact</p>
    * @param contactEntity the new contact information
    * @return ContactEntity the new created contact
    *
    */
    @Override
    public ContactEntity createContact(ContactEntity contactEntity) {
        return contactRepo.save(contactEntity);
    }

    /**
    * 
    * <p>Get contact by Id</p>
    * @param id the id that is used to find contact
    * @return ContactEntity the contact that is found
    *
    */
    @Override
    public ContactEntity getContactById(int id) throws EntityNotFoundException {
        Optional<ContactEntity> contactEntity = contactRepo.findById(id);
        if(contactEntity.isPresent() ){
            return contactEntity.get();
        } else {
            logger.error("Contact is not existed with id: {}", id);
            throw new EntityNotFoundException("Contact is not existed with id: " + id);
        }
    }

    /**
    * 
    * <p>Update Contact</p>
    * @param contactEntity the contact information
    * @return ContactEntity the updated contact
    *
    */
    @Override
    public ContactEntity updateContact(ContactEntity contactEntity) throws EntityNotFoundException {
        int id = contactEntity.getId();
        try {
            return contactRepo.save(contactEntity);
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.error("Contact is not existed with id: {}, {}", id, e);
            throw new EntityNotFoundException("Contact is not existed with id: " + id);
        }
        
    }

    /**
    * 
    * <p>Delete Contact</p>
    * @param id the id that is deleted
    *
    */
    @Override
    public void deleteContact(int id) {
        contactRepo.deleteById(id);
    }

    /**
    * 
    * <p>Find Contacts by first name or last name</p>
    * @param name the name is used to find contacts
    * @return the amount of contacts
    *
    */
    @Override
    public List<ContactEntity> searchByName(String name) {
        return contactRepo.findByName(name);
    }

}
