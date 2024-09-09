package com.ox.user.services;

import com.ox.user.converters.TaskConverter;
import com.ox.user.dto.TaskDTO;
import com.ox.user.entities.Task;
import com.ox.user.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskConverter taskConverter;

    private final TaskRepository taskRepository;

    public TaskDTO create(TaskDTO taskDTO) {
        Task task = taskRepository.save(taskConverter.fromDTO(taskDTO));
        return taskConverter.toDTO(task);
    }
}
