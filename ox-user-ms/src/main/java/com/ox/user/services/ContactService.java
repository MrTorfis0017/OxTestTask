package com.ox.user.services;

import com.ox.user.converters.ContactConverter;
import com.ox.user.dto.ContactDTO;
import com.ox.user.entities.Contact;
import com.ox.user.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
