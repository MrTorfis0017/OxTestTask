package com.ox.user.controllers;

import com.ox.user.dto.ClientDTO;
import com.ox.user.dto.ContactDTO;
import com.ox.user.services.ClientService;
import com.ox.user.services.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    @Operation(description = "Creating contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @PostMapping("/create")
    public ContactDTO createContact(@RequestBody ContactDTO contactDTO) {
        return contactService.create(contactDTO);
    }
}
