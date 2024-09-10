package com.ox.user.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ_ID")
    @SequenceGenerator(name = "ROLE_SEQ_ID", sequenceName = "ROLE_SEQ_ID", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;
}
