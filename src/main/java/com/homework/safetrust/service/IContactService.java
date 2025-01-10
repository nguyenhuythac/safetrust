package com.homework.safetrust.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.homework.safetrust.entity.ContactEntity;
import com.homework.safetrust.exception.EntityNotFoundException;

public interface IContactService {
    Page<ContactEntity> getContactsList(int page, int size);
    List<ContactEntity> getAllContacts();
    ContactEntity createContact(ContactEntity contactEntity);
    ContactEntity getContactById(int id) throws EntityNotFoundException;
    ContactEntity updateContact(ContactEntity contactEntity) throws EntityNotFoundException;
    void deleteContact(int id);
    List<ContactEntity> searchByName(String name);
}
