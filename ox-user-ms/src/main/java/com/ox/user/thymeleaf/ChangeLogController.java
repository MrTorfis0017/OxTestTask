package com.ox.user.thymeleaf;

import com.ox.user.entities.ChangeLog;
import com.ox.user.services.ChangeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChangeLogController {

    private final ChangeLogService changeLogService;

    @GetMapping("/changelogs/find-all")
    public List<ChangeLog> getChangeLogs() {
        return changeLogService.getAllChangeLogs();
    }
}

