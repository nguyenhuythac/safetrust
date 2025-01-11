package com.homework.safetrust.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.homework.safetrust.entity.ContactEntity;
import com.homework.safetrust.exception.EntityNotFoundException;
import com.homework.safetrust.repository.ContactRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ContactServiceTest {
    private ContactEntity contact;
    private List<ContactEntity> listContact;
    

    @Autowired
    IContactService contactService;

    @MockBean
    ContactRepository contactRepo;

    @BeforeAll
    public void setup() {
        contact = new ContactEntity(0, "contact Name 1", "contact1@gmail.com", "0906604279", "710000");
        ContactEntity contact2 = new ContactEntity(0, "contact Name 2", "contact2@gmail.com", "0906604279", "720000");
        listContact = Stream.of(contact,contact2).collect(Collectors.toList());
    }
    @Test
    void getAllContactsTest_Success() {
        when(contactRepo.findAll())
                .thenReturn(listContact);
        assertEquals(2, contactService.getAllContacts().size());
    }

    @Test
    void getContactsListTest_Pagination_Success() {
        PageRequest pageReq = PageRequest.of(0, 1);
        Page<ContactEntity> contactPage = new PageImpl<>(listContact, pageReq, listContact.size());
        when(contactRepo.findAll(any(PageRequest.class))).thenReturn(contactPage);
        assertEquals(1, contactService.getContactsList(0, 1).getSize());
    }

    @Test
    void createContactTest_Success() {
        when(contactRepo.save(any(ContactEntity.class))).thenReturn(contact);
        assertEquals("contact Name 1", contactService.createContact(contact).getName());
    }

    @Test
    void getContactByIdTest_Success() throws EntityNotFoundException {
		when(contactRepo.findById(any(Integer.class))).thenReturn(Optional.of(contact));
		assertEquals("contact1@gmail.com", contactService.getContactById(0).getEmail());
	}

    @Test
    void getContactByIdTest_ThrowException() throws EntityNotFoundException {
		when(contactRepo.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));
        
	}

    @Test
    void updateContactTest() throws EntityNotFoundException {
		when(contactRepo.save(any(ContactEntity.class))).thenReturn(contact);
		assertEquals("0906604279", contactService.updateContact(contact).getPhone());
	}

    @Test
    void updateContactTest_ThrowException() throws EntityNotFoundException {
		when(contactRepo.findById(any(Integer.class))).thenThrow(new ObjectOptimisticLockingFailureException("1","1"));
	}

    @Test
    void searchByNameTest_Success() throws EntityNotFoundException {
		when(contactRepo.findByName(any(String.class))).thenReturn(listContact);
		assertEquals(2, listContact.size());
	}

}
