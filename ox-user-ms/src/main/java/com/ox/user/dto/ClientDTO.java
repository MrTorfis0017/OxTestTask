package com.ox.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientDTO {

    private Long id;

    private String companyName;

    private String industry;

    private String address;

    private List<ContactDTO> contacts;
}
