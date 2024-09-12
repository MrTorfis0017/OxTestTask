package com.ox.user.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ox.user.dto.ClientDTO;
import com.ox.user.entities.Client;
import com.ox.user.repositories.ClientRepository;
import com.ox.user.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTest extends BaseTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Create client")
    public void createClientTest() throws Exception {
        ClientDTO clientDTO = createClientDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(clientDTO);

        ResultActions resultActions = mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

        Client result = clientRepository.getReferenceById(clientDTO.getId());
        assertEquals(json, resultActions.andReturn().getResponse().getContentAsString());
        assertEquals(clientDTO.getId(), result.getId());
        assertFalse(clientRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Update client")
    public void updateClientTest() throws Exception {
        ClientDTO clientDTO = createClientDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(clientDTO);

        ResultActions resultActions = mockMvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

        Client result = clientRepository.getReferenceById(clientDTO.getId());
        assertEquals(json, resultActions.andReturn().getResponse().getContentAsString());
        assertEquals(clientDTO.getId(), result.getId());
        assertFalse(clientRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Get client by id")
    public void getClientByIdTest() throws Exception {
        ClientDTO clientDTO = createClientDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/client/get/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

        Client result = clientRepository.getReferenceById(clientDTO.getId());
        assertEquals(json, resultActions.andReturn().getResponse().getContentAsString());
        assertEquals(clientDTO.getId(), result.getId());
        assertFalse(clientRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Delete client")
    public void deleteClientTest() throws Exception {
        ClientDTO clientDTO = createClientDTO(2L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/client/delete/2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    @DisplayName("Find client by company name")
    public void findByNameTest() throws Exception {
        ClientDTO clientDTO = createClientDTO(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get("/api/client/find-by-name/com").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

        assertFalse(resultActions.andReturn().getResponse().getContentAsString().isEmpty());
    }

    @Test
    @DisplayName("Find client by address")
    public void findByAddressTest() throws Exception {
        ClientDTO clientDTO = createClientDTO(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/client/find-by-address/add").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

        assertFalse(resultActions.andReturn().getResponse().getContentAsString().isEmpty());
    }

    @Test
    @DisplayName("Find all existed clients")
    public void findAllTest() throws Exception {
        ClientDTO clientDTO = createClientDTO(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/client/find-all").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

        assertFalse(resultActions.andReturn().getResponse().getContentAsString().isEmpty());
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
