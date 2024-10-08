package com.ox.user.services;

import com.ox.user.converters.ClientConverter;
import com.ox.user.dto.ClientDTO;
import com.ox.user.entities.Client;
import com.ox.user.repositories.ClientRepository;
import com.ox.user.repositories.specifications.ClientSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientConverter clientConverter;

    private final ClientRepository clientRepository;

    @CacheEvict(value = "clients", allEntries = true)
    public ClientDTO create(ClientDTO clientDTO) {
        Client client = clientRepository.save(clientConverter.fromDTO(clientDTO));
        return clientConverter.toDTO(client);
    }

    @CacheEvict(value = "clients", allEntries = true)
    public ClientDTO update(ClientDTO clientDTO) {
        Client client = clientRepository.save(clientConverter.fromDTO(clientDTO));
        return clientConverter.toDTO(client);
    }

    public ClientDTO get(Long id) {
        return clientConverter.toDTO(clientRepository.getReferenceById(id));
    }

    @CacheEvict(value = "clients", allEntries = true)
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public List<ClientDTO> searchByName(String name) {
        return clientRepository.findAll(ClientSpecifications.hasName(name)).stream().map(clientConverter::toDTO).toList();
    }

    public List<ClientDTO> searchByAddress(String name) {
        return clientRepository.findAll(ClientSpecifications.hasAddress(name)).stream().map(clientConverter::toDTO).toList();
    }

    @Cacheable(value = "clients")
    public List<ClientDTO> findAll() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return clientRepository.findAll().stream().map(clientConverter::toDTO).toList();
    }
}
