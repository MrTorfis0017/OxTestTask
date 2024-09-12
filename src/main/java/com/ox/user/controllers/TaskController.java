package com.ox.user.controllers;

import com.ox.user.dto.TaskDTO;
import com.ox.user.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @Operation(description = "Creating task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @PostMapping("/create")
    public TaskDTO createContact(@RequestBody TaskDTO taskDTO) {
        return taskService.create(taskDTO);
    }

    @Operation(description = "Update task. Status, related contact and so on.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @PutMapping("/update")
    public TaskDTO changeStatus(@RequestBody TaskDTO taskDTO) {
        return taskService.update(taskDTO);
    }

    @Operation(description = "Get task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @GetMapping("/get/{id}")
    public TaskDTO getContact(@PathVariable Long id) {
        return taskService.get(id);
    }

    @Operation(description = "Find all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @GetMapping("/find-all")
    public List<TaskDTO> findAllTasks() {
        return taskService.findAll();
    }

    @Operation(description = "Delete task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteContact(@PathVariable Long id) {
        taskService.delete(id);
    }
}