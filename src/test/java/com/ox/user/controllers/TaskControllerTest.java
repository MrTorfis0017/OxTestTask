package com.ox.user.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ox.user.dto.TaskDTO;
import com.ox.user.dto.WorkStatus;
import com.ox.user.entities.Task;
import com.ox.user.repositories.TaskRepository;
import com.ox.user.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerTest extends BaseTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Create task")
    public void createTaskTest() throws Exception {
        TaskDTO taskDTO = createTaskDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(taskDTO);

        mockMvc.perform(post("/api/task/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

        Task result = taskRepository.getReferenceById(taskDTO.getId());
        assertEquals(taskDTO.getId(), result.getId());
        assertFalse(taskRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Update task")
    public void updateTaskTest() throws Exception {
        TaskDTO taskDTO = createTaskDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(taskDTO);

        mockMvc.perform(post("/api/task/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/task/update").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

        Task result = taskRepository.getReferenceById(taskDTO.getId());
        assertEquals(taskDTO.getId(), result.getId());
        assertFalse(taskRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Get task by id")
    public void getTaskByIdTest() throws Exception {
        TaskDTO taskDTO = createTaskDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(taskDTO);

        mockMvc.perform(post("/api/task/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/get/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

        Task result = taskRepository.getReferenceById(taskDTO.getId());
        assertEquals(taskDTO.getId(), result.getId());
        assertFalse(taskRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Delete task")
    public void deleteTaskTest() throws Exception {
        TaskDTO taskDTO = createTaskDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(taskDTO);

        mockMvc.perform(post("/api/task/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/task/delete/2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @Test
    @DisplayName("Find all existed tasks")
    public void findAllTest() throws Exception {
        TaskDTO taskDTO = createTaskDTO(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(taskDTO);

        mockMvc.perform(post("/api/task/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/task/find-all").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

        assertFalse(resultActions.andReturn().getResponse().getContentAsString().isEmpty());
    }

    private TaskDTO createTaskDTO(Long id) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setDescription("Description");
        taskDTO.setWorkStatus(WorkStatus.OPEN);
        taskDTO.setEndDate(new Date());
        return taskDTO;
    }
}
