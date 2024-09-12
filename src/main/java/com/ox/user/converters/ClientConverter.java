package com.ox.user.converters;

import com.ox.user.dto.ClientDTO;
import com.ox.user.dto.ContactDTO;
import com.ox.user.entities.Client;
import com.ox.user.entities.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientConverter {

    private final ContactConverter contactConverter;

    public ClientDTO toDTO(Client client) {
        ClientDTO result = new ClientDTO();
        result.setId(client.getId());
        result.setCompanyName(client.getCompanyName());
        result.setIndustry(client.getIndustry());
        result.setAddress(client.getAddress());
        if (client.getContacts() != null) {
            List<ContactDTO> contactDTOList = new ArrayList<>();
            for (Contact item : client.getContacts()) {
                contactDTOList.add(contactConverter.toDTO(item));
            }
            result.setContacts(contactDTOList);
        } else
            result.setContacts(null);
        return result;
    }

    public Client fromDTO(ClientDTO clientDTO) {
        Client result = new Client();
        result.setId(clientDTO.getId());
        result.setCompanyName(clientDTO.getCompanyName());
        result.setIndustry(clientDTO.getIndustry());
        result.setAddress(clientDTO.getAddress());
        if (clientDTO.getContacts() != null) {
            List<Contact> contactList = new ArrayList<>();
            for (ContactDTO item : clientDTO.getContacts()) {
                contactList.add(contactConverter.fromDTO(item));
            }
            result.setContacts(contactList);
        } else
            result.setContacts(null);
        return result;
    }

}
