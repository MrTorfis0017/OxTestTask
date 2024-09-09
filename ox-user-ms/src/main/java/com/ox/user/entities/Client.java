package com.ox.user.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "CLIENT")
public class Client {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "INDUSTRY")
    private String industry;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(mappedBy = "client")
    private List<Contact> contacts;
}
