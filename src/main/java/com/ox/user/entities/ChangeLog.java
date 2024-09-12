package com.ox.user.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "CHANGE_LOG")
@Getter
@Setter
public class ChangeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "TIMESTAMP")
    private Date timestamp;
}

