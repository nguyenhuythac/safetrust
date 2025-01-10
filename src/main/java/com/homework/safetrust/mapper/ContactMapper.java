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
* @author Thac Nguyen
*/
@Component
public class ContactMapper {
    @Autowired
    private ModelMapper modelMapper;

    /**
    * 
    * <p>Convert entity to DTO</p>
    * @param ContactEntity Contact Entity convert
    * @return Contact dto
    *
    */
    public Contact convertToDto(ContactEntity entity) {
        return modelMapper.map(entity, Contact.class);
    }

    /**
    * 
    * <p>Convert DTO  To Entity</p>
    * @param Contact Contact DTO convert
    * @return ContactEntity 
    *
    */
    public ContactEntity convertToEntity(Contact contact){
        return modelMapper.map(contact, ContactEntity.class);
    }
}
