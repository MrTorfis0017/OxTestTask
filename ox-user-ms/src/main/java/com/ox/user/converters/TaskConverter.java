package com.ox.user.converters;

import com.ox.user.dto.TaskDTO;
import com.ox.user.entities.Task;
import com.ox.user.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskConverter {

    private final ContactRepository contactRepository;

    public TaskDTO toDTO(Task task) {
        TaskDTO result = new TaskDTO();
        result.setId(task.getId());
        result.setDescription(task.getDescription());
        result.setEndDate(task.getEndDate());
        result.setWorkStatus(task.getWorkStatus());
        result.setContactId(task.getContact().getId());
        return result;
    }

    public Task fromDTO(TaskDTO taskDTO) {
        Task result = new Task();
        result.setId(taskDTO.getId());
        result.setDescription(taskDTO.getDescription());
        result.setEndDate(taskDTO.getEndDate());
        result.setWorkStatus(taskDTO.getWorkStatus());
        if (taskDTO.getContactId() != null) {
            result.setContact(contactRepository.getReferenceById(taskDTO.getContactId()));
        }
        return result;
    }
}
