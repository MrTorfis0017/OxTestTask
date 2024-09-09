package com.ox.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    private Long id;

    private String description;

    private WorkStatus workStatus;

    private String endDate;

    private Long contactId;
}
