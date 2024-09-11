package com.ox.user.services;

import com.ox.user.entities.ChangeLog;
import com.ox.user.repositories.ChangeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChangeLogService {

    private final ChangeLogRepository changeLogRepository;

    public List<ChangeLog> getAllChangeLogs() {
        return changeLogRepository.findAllByOrderByTimestampDesc();
    }

    public void addChangeLog(ChangeLog changeLog) {
        changeLogRepository.save(changeLog);
    }
}

