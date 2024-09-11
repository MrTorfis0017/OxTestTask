package com.ox.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ClientDTO implements Serializable {

    private Long id;

    private String companyName;

    private String industry;

    private String address;

    private List<ContactDTO> contacts;
}
