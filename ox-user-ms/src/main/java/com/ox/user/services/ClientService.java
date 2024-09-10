package com.ox.user.services;

import com.ox.user.converters.ClientConverter;
import com.ox.user.dto.ClientDTO;
import com.ox.user.entities.Client;
import com.ox.user.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientConverter clientConverter;

    private final ClientRepository clientRepository;

    public ClientDTO create(ClientDTO clientDTO) {
        Client client = clientRepository.save(clientConverter.fromDTO(clientDTO));
        return clientConverter.toDTO(client);
    }

    public ClientDTO update(ClientDTO clientDTO) {
        Client client = clientRepository.save(clientConverter.fromDTO(clientDTO));
        return clientConverter.toDTO(client);
    }

    public ClientDTO get(Long id) {
        return clientConverter.toDTO(clientRepository.getReferenceById(id));
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
