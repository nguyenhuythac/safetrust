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
* 
*/
@Service
public class ContactService implements IContactService{

    private Logger logger  = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactRepository contactRepo;

    public Page<ContactEntity> getContactsList(int offset, int pageSize) {
        PageRequest pageReq = PageRequest.of(offset, pageSize);
        Page<ContactEntity> contacts = contactRepo.findAll(pageReq);
        return contacts;
    }

    @Override
    public List<ContactEntity> getAllContacts() {
        return contactRepo.findAll();
    }

    @Override
    public ContactEntity createContact(ContactEntity contactEntity) {
        return contactRepo.save(contactEntity);
    }

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

    @Override
    public void deleteContact(int id) {
        contactRepo.deleteById(id);
    }

    @Override
    public List<ContactEntity> searchByName(String name) {
        return contactRepo.findByName(name);
    }

}
