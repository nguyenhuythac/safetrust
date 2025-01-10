package com.homework.safetrust.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homework.safetrust.entity.ContactEntity;
import com.homework.safetrust.model.Contact;
/**
* 
* This class is used to map between DTO model and entity
* 
*/
@Component
public class ContactMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Contact convertToDto(ContactEntity entity) {
        return modelMapper.map(entity, Contact.class);
    }

    public ContactEntity convertToEntity(Contact contact){
        return modelMapper.map(contact, ContactEntity.class);
    }
}
