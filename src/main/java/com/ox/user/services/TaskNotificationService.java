package com.ox.user.services;

import com.ox.user.entities.ChangeLog;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskNotificationService {

    private final ChangeLogService changeLogService;

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyTaskUpdated(ChangeLog changeLog) {
        messagingTemplate.convertAndSend("/topic/task-updates", changeLog);
        changeLogService.addChangeLog(changeLog);
    }
}
