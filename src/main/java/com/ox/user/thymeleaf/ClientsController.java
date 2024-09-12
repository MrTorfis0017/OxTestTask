package com.ox.user.thymeleaf;

import com.ox.user.dto.ClientDTO;
import com.ox.user.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientsController {

    private final ClientService clientService;

    @GetMapping("/clients")
    public String getAllClients(Model model) {
        List<ClientDTO> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "client/clients";
    }

    @GetMapping("/create")
    public String showCreateClientPage() {
        return "client/create-client";
    }

    @GetMapping("/client/{id}")
    public String showClientDetails(@PathVariable("id") Long id, Model model) {
        ClientDTO client = clientService.get(id);
        model.addAttribute("client", client);
        return "client/client-details";
    }
}
