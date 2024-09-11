package com.ox.user.services;

import com.ox.user.dto.WorkStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public TaskNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyTaskUpdated(Long taskId, WorkStatus status, Date endDate) {
        messagingTemplate.convertAndSend("/topic/task-updates", taskId);
    }

}
