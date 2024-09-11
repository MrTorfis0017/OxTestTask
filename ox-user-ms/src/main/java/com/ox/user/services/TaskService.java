package com.ox.user.services;

import com.ox.user.converters.TaskConverter;
import com.ox.user.dto.TaskDTO;
import com.ox.user.entities.Task;
import com.ox.user.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskConverter taskConverter;

    private final TaskRepository taskRepository;

    private final TaskNotificationService notificationService;

    public TaskDTO create(TaskDTO taskDTO) {
        Task task = taskRepository.save(taskConverter.fromDTO(taskDTO));
        return taskConverter.toDTO(task);
    }

    public TaskDTO update(TaskDTO taskDTO) {
        Task task = taskRepository.save(taskConverter.fromDTO(taskDTO));
        notificationService.notifyTaskUpdated(task.getId(), task.getWorkStatus(), task.getEndDate());
        return taskConverter.toDTO(task);
    }

    public List<TaskDTO> findAll() {
        return taskRepository.findAll().stream().map(taskConverter::toDTO).toList();
    }

    public TaskDTO get(Long id) {
        return taskConverter.toDTO(taskRepository.getReferenceById(id));
    }
}
