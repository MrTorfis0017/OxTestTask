package com.ox.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class TaskDTO {

    private Long id;

    private String description;

    private WorkStatus workStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Long contactId;
}
