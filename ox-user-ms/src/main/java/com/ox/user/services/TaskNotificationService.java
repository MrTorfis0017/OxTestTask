package com.ox.user.services;

import com.ox.user.dto.WorkStatus;
import com.ox.user.entities.ChangeLog;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TaskNotificationService {

    private final ChangeLogService changeLogService;

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyTaskUpdated(Long taskId, WorkStatus status, Date endDate) {
        messagingTemplate.convertAndSend("/topic/task-updates", taskId);
        ChangeLog changeLog = new ChangeLog();
        changeLog.setDetails("Tasks with id " + taskId + " update status: " + status.name() + " " + endDate);
        changeLog.setType("UPDATE");
        changeLog.setTimestamp(new Date());
        changeLogService.addChangeLog(changeLog);
    }
}
