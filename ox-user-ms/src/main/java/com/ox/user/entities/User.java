package com.ox.user.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "APP_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_USER_SEQ_ID")
    @SequenceGenerator(name = "APP_USER_SEQ_ID", sequenceName = "APP_USER_SEQ_ID", allocationSize = 1)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "APP_USER_ROLE",
            joinColumns = @JoinColumn(name = "APP_USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
}
