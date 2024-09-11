package com.ox.user.services;

import com.ox.user.converters.ContactConverter;
import com.ox.user.dto.ContactDTO;
import com.ox.user.entities.Contact;
import com.ox.user.repositories.ContactRepository;
import com.ox.user.repositories.specifications.ContactSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactConverter contactConverter;

    private final ContactRepository contactRepository;

    public ContactDTO create(ContactDTO contactDTO) {
        Contact contact = contactRepository.save(contactConverter.fromDTO(contactDTO));
        return contactConverter.toDTO(contact);
    }

    public ContactDTO update(ContactDTO contactDTO) {
        Contact contact = contactRepository.save(contactConverter.fromDTO(contactDTO));
        return contactConverter.toDTO(contact);
    }

    public ContactDTO get(Long id) {
        return contactConverter.toDTO(contactRepository.getReferenceById(id));
    }

    public void delete(Long id) {
        contactRepository.deleteById(id);
    }

    public List<ContactDTO> findAll() {
        return contactRepository.findAll().stream().map(contactConverter::toDTO).toList();
    }

    public List<ContactDTO> searchByLastName(String lastname) {
        return contactRepository.findAll(ContactSpecification.hasLastName(lastname)).stream().map(contactConverter::toDTO).toList();
    }

    public List<ContactDTO> searchByEmail(String email) {
        return contactRepository.findAll(ContactSpecification.hasEmail(email)).stream().map(contactConverter::toDTO).toList();
    }
}
