package com.ox.user.services;

import com.ox.user.converters.TaskConverter;
import com.ox.user.dto.TaskDTO;
import com.ox.user.entities.ChangeLog;
import com.ox.user.entities.Task;
import com.ox.user.repositories.ContactRepository;
import com.ox.user.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskConverter taskConverter;

    private final TaskRepository taskRepository;

    private final TaskNotificationService notificationService;
    private final ContactRepository contactRepository;

    public TaskDTO create(TaskDTO taskDTO) {
        Task task = taskRepository.save(taskConverter.fromDTO(taskDTO));
        return taskConverter.toDTO(task);
    }

    public TaskDTO update(TaskDTO taskDTO) {
        Task taskForDifferences = taskRepository.getReferenceById(taskDTO.getId());
        checkDifferences(taskDTO, taskForDifferences);
        Task task = taskRepository.save(taskConverter.fromDTO(taskDTO));
        return taskConverter.toDTO(task);
    }

    public List<TaskDTO> findAll() {
        return taskRepository.findAll().stream().map(taskConverter::toDTO).toList();
    }

    public TaskDTO get(Long id) {
        return taskConverter.toDTO(taskRepository.getReferenceById(id));
    }

    private void checkDifferences(TaskDTO taskDTO, Task taskForDifferences) {
        ChangeLog changeLog = new ChangeLog();
        if (!taskDTO.getWorkStatus().equals(taskForDifferences.getWorkStatus())) {
            changeLog.setDetails("Task with id " + taskDTO.getId() + " update status from " + taskForDifferences.getWorkStatus() + " to: " + taskDTO.getWorkStatus());
            changeLog.setType("UPDATE");
            changeLog.setTimestamp(new Date());
            notificationService.notifyTaskUpdated(changeLog);
        } else if (!taskDTO.getEndDate().equals(taskForDifferences.getEndDate())) {
            changeLog.setDetails("Task with id " + taskDTO.getId() + " update endDate from " + taskForDifferences.getEndDate() + " to: " + taskDTO.getEndDate());
            changeLog.setType("UPDATE");
            changeLog.setTimestamp(new Date());
            notificationService.notifyTaskUpdated(changeLog);
        }

    }

    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
