package com.ox.user.entities;

import com.ox.user.dto.WorkStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "TASK")
public class Task {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "WORK_STATUS")
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;

    @Column(name = "END_DATE")
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact;
}
