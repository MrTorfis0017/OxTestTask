package com.ox.user.repositories;

import com.ox.user.entities.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChangeLogRepository extends JpaRepository<ChangeLog, Long> {
    List<ChangeLog> findAllByOrderByTimestampDesc();
}
