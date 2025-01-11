package com.homework.safetrust.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import com.homework.safetrust.entity.ContactEntity;

@DataJpaTest
@Transactional
@AutoConfigureTestEntityManager
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepo;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findByName_whenFindByName_thenSuccess() {
        ContactEntity newContact = new ContactEntity();
        newContact.setName("name");
        newContact.setEmail("contact3@gmail.com");
        newContact.setPhone("0906604279");
        newContact.setPostal("730000");
        entityManager.persist(newContact);
        List<ContactEntity> ContactEntityList = contactRepo.findByName("name");
        assertEquals(ContactEntityList.size(), 1);
    }
}
