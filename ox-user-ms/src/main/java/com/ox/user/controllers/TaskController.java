package com.ox.user.controllers;

import com.ox.user.dto.ContactDTO;
import com.ox.user.dto.TaskDTO;
import com.ox.user.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
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
    @PostMapping("/update")
    public TaskDTO changeStatus(@RequestBody TaskDTO taskDTO) {
        return taskService.update(taskDTO);
    }
}