package com.ox.user.thymeleaf;

import com.ox.user.dto.ContactDTO;
import com.ox.user.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ContactsController {

    private final ContactService contactService;

    @GetMapping("/contacts/create")
    public String showCreateContactPage() {
        return "contact/create-contact";
    }

    @GetMapping("/contacts")
    public String showContacts(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "contact/contacts";
    }

    @GetMapping("/contact/{id}")
    public String showContactDetails(@PathVariable("id") Long id, Model model) {
        ContactDTO contact = contactService.get(id);
        model.addAttribute("contact", contact);
        return "contact/contact-details";
    }
}
