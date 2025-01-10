package com.homework.safetrust.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homework.safetrust.entity.ContactEntity;
import com.homework.safetrust.exception.EntityNotFoundException;
import com.homework.safetrust.exception.UnmatchIDException;
import com.homework.safetrust.mapper.ContactMapper;
import com.homework.safetrust.model.Contact;
import com.homework.safetrust.service.IContactService;

import jakarta.validation.Valid;

/**
* 
* Contact controller Rest api class.
* 
*/
@RestController
@RequestMapping("/contact")
public class ContactController {
    private Logger logger  = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private IContactService contactService;

    @Autowired
    ContactMapper contactMapper;

    @GetMapping("pagination/{offset}/{pageSize}")
    public List<Contact> getContacts(@PathVariable int offset, @PathVariable int pageSize) {
        logger.info("Get list contact with offset: {}, pageSize: {} ", offset, pageSize);
        Page<ContactEntity> contacts = contactService.getContactsList(offset, pageSize);
        logger.info("list contact object query: {} ", contactService.toString());
        return contacts.stream().map(contact -> contactMapper.convertToDto(contact)).collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<Contact> searchByName(@RequestParam String name) {
    logger.info("search all contact with name: {}" , name);
    List<ContactEntity> contacts = contactService.searchByName(name);
    logger.info("total contact search result with name: {}" , contacts.size());
    return contacts.stream().map(contact ->
        contactMapper.convertToDto(contact)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable int id) throws EntityNotFoundException {
        ContactEntity contactEntity = contactService.getContactById(id);
        logger.info("Get contact by ID: {} result: {}" , id, contactEntity);
        return contactMapper.convertToDto(contactEntity);
    }

    @PostMapping
    public ResponseEntity<ContactEntity> createContact(@RequestBody @Valid Contact contact) {
        ContactEntity contactEntity = contactMapper.convertToEntity(contact);
        logger.info("created contact : {}", contact.toString());
        return new ResponseEntity<>(contactService.createContact(contactEntity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactEntity> updateContact(@PathVariable int id, @RequestBody @Valid Contact contact)
            throws EntityNotFoundException, UnmatchIDException {
        if (id != contact.getId()) {
            logger.error("ID in URL and Body don't match");
            throw new UnmatchIDException("ID in URL and Body don't match");
        }
        contact.setId(id);
        ContactEntity contactEntity = contactMapper.convertToEntity(contact);
        return new ResponseEntity<>(contactService.updateContact(contactEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable int id) {
        logger.info("delete contact with id : {}", id);
        contactService.deleteContact(id);
    }

}
