package com.homework.safetrust.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.safetrust.SafetrustApplication;
import com.homework.safetrust.entity.ContactEntity;
import com.homework.safetrust.service.IContactService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {SafetrustApplication.class})
@TestInstance(Lifecycle.PER_CLASS)
@Import(ContactController.class)
public class ContactControllerTest {
    private ContactEntity contact;
    private List<ContactEntity> listContact;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public void setup() {
        contact = new ContactEntity(1, "contact Name 1", "contact1@gmail.com", "0906604279", "710000");
        ContactEntity contact2 = new ContactEntity(2, "contact Name 2", "contact2@gmail.com", "0906604279", "720000");
        listContact = Stream.of(contact, contact2).collect(Collectors.toList());
    }

    @Test
    void getContactsSuccessCaseTest() throws Exception {
        PageRequest pageReq = PageRequest.of(0, 1);
        Page<ContactEntity> contactPage = new PageImpl<>(listContact, pageReq, listContact.size());
        when(contactService.getContactsList(anyInt(), anyInt())).thenReturn(contactPage);
        mockMvc.perform(get("/contact/pagination/0/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactPage))).andExpect(status().isOk());
    }

    @Test
    void searchByNameSuccessCaseTest() throws Exception {
        when(contactService.searchByName(anyString())).thenReturn(listContact);
        ResultActions response =this.mockMvc.perform(get("/contact/search?name=contact").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(listContact)));
        response.andExpect(status().isOk());
    }

    @Test
    void getContactByIdSuccessCaseTest() throws Exception {
        when(contactService.getContactById(anyInt())).thenReturn(contact);
        this.mockMvc.perform(get("/contact/1").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(contact))).andExpect(status().isOk());
    }

    @Test
    void createContactSuccessCaseTest() throws Exception {
        ContactEntity newContact = new ContactEntity();
        newContact.setName("contact Name new");
        newContact.setEmail("contact3@gmail.com");
        newContact.setPhone("0906604279");
        newContact.setPostal("730000");
        when(contactService.getContactById(anyInt())).thenReturn(newContact);
        mockMvc.perform(post("/contact").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newContact))).andExpect(status().isCreated());
    }

    @Test
    void updateContactSuccessCaseTest() throws Exception {
        when(contactService.updateContact(any(ContactEntity.class))).thenReturn(contact);
        this.mockMvc.perform(put("/contact/1").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(contact))).andExpect(status().isOk());
    }

    @Test
    void updateContactThrowUnmatchIDExceptionTest() throws Exception {
        when(contactService.updateContact(any(ContactEntity.class))).thenReturn(contact);
        this.mockMvc.perform(put("/contact/2")).andExpect(status().isBadRequest());
    }
}
