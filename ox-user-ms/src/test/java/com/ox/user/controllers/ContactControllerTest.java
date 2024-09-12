package com.ox.user.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ox.user.dto.ClientDTO;
import com.ox.user.dto.ContactDTO;
import com.ox.user.entities.Contact;
import com.ox.user.repositories.ContactRepository;
import com.ox.user.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContactControllerTest extends BaseTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    @DisplayName("Create contact")
    public void createContactTest() throws Exception {
        ContactDTO contactDTO = createContactDTO(1L);
        ClientDTO clientDTO = createClientDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonClient = objectMapper.writeValueAsString(clientDTO);
        String jsonContact = objectMapper.writeValueAsString(contactDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(jsonClient)).andExpect(status().isOk());
        ResultActions resultActions = mockMvc.perform(post("/api/contact/create").contentType(MediaType.APPLICATION_JSON).content(jsonContact)).andExpect(status().isOk());

        Contact result = contactRepository.getReferenceById(contactDTO.getId());
        assertEquals(jsonContact, resultActions.andReturn().getResponse().getContentAsString());
        assertEquals(contactDTO.getId(), result.getId());
        assertFalse(contactRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Update contact")
    public void updateContactTest() throws Exception {
        ContactDTO contactDTO = createContactDTO(1L);
        ClientDTO clientDTO = createClientDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(contactDTO);
        String jsonClient = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(jsonClient)).andExpect(status().isOk());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/contact/update").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

        Contact result = contactRepository.getReferenceById(contactDTO.getId());
        assertEquals(json, resultActions.andReturn().getResponse().getContentAsString());
        assertEquals(contactDTO.getId(), result.getId());
        assertFalse(contactRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Get contact by id")
    public void getContactByIdTest() throws Exception {
        ContactDTO contactDTO = createContactDTO(1L);
        ClientDTO clientDTO = createClientDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContact = objectMapper.writeValueAsString(contactDTO);
        String jsonClient = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(jsonClient)).andExpect(status().isOk());
        mockMvc.perform(post("/api/contact/create").contentType(MediaType.APPLICATION_JSON).content(jsonContact)).andExpect(status().isOk());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/contact/get/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));


        Contact result = contactRepository.getReferenceById(clientDTO.getId());
        assertEquals(jsonContact, resultActions.andReturn().getResponse().getContentAsString());
        assertEquals(clientDTO.getId(), result.getId());
        assertFalse(contactRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Delete contact")
    public void deleteContactTest() throws Exception {
        ContactDTO contactDTO = createContactDTO(2L);
        ClientDTO clientDTO = createClientDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContact = objectMapper.writeValueAsString(contactDTO);
        String jsonClient = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(jsonClient)).andExpect(status().isOk());
        mockMvc.perform(post("/api/contact/create").contentType(MediaType.APPLICATION_JSON).content(jsonContact)).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/contact/delete/2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Find contact by lastname")
    public void findByLastNameTest() throws Exception {
        ContactDTO contactDTO = createContactDTO(1L);
        ClientDTO clientDTO = createClientDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContact = objectMapper.writeValueAsString(contactDTO);
        String jsonClient = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(jsonClient)).andExpect(status().isOk());
        mockMvc.perform(post("/api/contact/create").contentType(MediaType.APPLICATION_JSON).content(jsonContact)).andExpect(status().isOk());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/contact/find-by-lastname/pob").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

        assertFalse(resultActions.andReturn().getResponse().getContentAsString().isEmpty());
    }

    @Test
    @DisplayName("Find contact by email")
    public void findByEmailTest() throws Exception {
        ContactDTO contactDTO = createContactDTO(1L);
        ClientDTO clientDTO = createClientDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContact = objectMapper.writeValueAsString(contactDTO);
        String jsonClient = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(jsonClient)).andExpect(status().isOk());
        mockMvc.perform(post("/api/contact/create").contentType(MediaType.APPLICATION_JSON).content(jsonContact)).andExpect(status().isOk());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/contact/find-by-email/sasap").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

        assertFalse(resultActions.andReturn().getResponse().getContentAsString().isEmpty());
    }

    @Test
    @DisplayName("Find all existed contacts")
    public void findAllTest() throws Exception {
        ContactDTO contactDTO = createContactDTO(1L);
        ClientDTO clientDTO = createClientDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContact = objectMapper.writeValueAsString(contactDTO);
        String jsonClient = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(jsonClient)).andExpect(status().isOk());
        mockMvc.perform(post("/api/contact/create").contentType(MediaType.APPLICATION_JSON).content(jsonContact)).andExpect(status().isOk());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/contact/find-all").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

        assertFalse(resultActions.andReturn().getResponse().getContentAsString().isEmpty());
    }

    private ContactDTO createContactDTO(Long id) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(id);
        contactDTO.setFirstName("Aleksandr");
        contactDTO.setLastName("Pobizinskyi");
        contactDTO.setPhoneNumber("+380661928357");
        contactDTO.setEmail("sasapobizinskij@gmail.com");
        contactDTO.setClientId(1L);
        contactDTO.setTasks(new ArrayList<>());
        return contactDTO;
    }

    private ClientDTO createClientDTO(Long id) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(id);
        clientDTO.setCompanyName("OX Company");
        clientDTO.setIndustry("IT");
        clientDTO.setAddress("Some Address");
        clientDTO.setContacts(new ArrayList<>());
        return clientDTO;
    }

}
