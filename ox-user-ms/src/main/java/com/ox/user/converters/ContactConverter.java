package com.ox.user.converters;

import com.ox.user.dto.ContactDTO;
import com.ox.user.dto.TaskDTO;
import com.ox.user.entities.Contact;
import com.ox.user.entities.Task;
import com.ox.user.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactConverter {

    private final TaskConverter taskConverter;

    private final ClientRepository clientRepository;

    public ContactDTO toDTO(Contact contact) {
        ContactDTO result = new ContactDTO();
        result.setId(contact.getId());
        result.setFirstName(contact.getFirstName());
        result.setLastName(contact.getLastName());
        result.setEmail(contact.getEmail());
        result.setPhoneNumber(contact.getPhoneNumber());
        result.setClientId(contact.getClient().getId());
        if (contact.getTasks() != null) {
            List<TaskDTO> taskDTOList = new ArrayList<>();
            for (Task item : contact.getTasks()) {
                taskDTOList.add(taskConverter.toDTO(item));
            }
            result.setTasks(taskDTOList);
        } else
            result.setTasks(null);
        return result;
    }

    public Contact fromDTO(ContactDTO contactDTO) {
        Contact result = new Contact();
        result.setId(contactDTO.getId());
        result.setFirstName(contactDTO.getFirstName());
        result.setLastName(contactDTO.getLastName());
        result.setEmail(contactDTO.getEmail());
        result.setPhoneNumber(contactDTO.getPhoneNumber());
        result.setClient(clientRepository.getReferenceById(contactDTO.getClientId()));
        if (contactDTO.getTasks() != null) {
            List<Task> taskList = new ArrayList<>();
            for (TaskDTO item : contactDTO.getTasks()) {
                taskList.add(taskConverter.fromDTO(item));
            }
            result.setTasks(taskList);
        } else
            result.setTasks(null);

        return result;
    }
}
