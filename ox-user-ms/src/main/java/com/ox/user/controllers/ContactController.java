package com.ox.user.controllers;

import com.ox.user.dto.ClientDTO;
import com.ox.user.dto.ContactDTO;
import com.ox.user.services.ClientService;
import com.ox.user.services.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @Operation(description = "Update contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @PutMapping("/update")
    public ContactDTO updateClient(@RequestBody ContactDTO contactDTO) {
        return contactService.update(contactDTO);
    }

    @Operation(description = "Get contact by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @GetMapping("/get/{id}")
    public ContactDTO getContact(@PathVariable Long id) {
        return contactService.get(id);
    }

    @Operation(description = "Delete contact by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        contactService.delete(id);
    }
}
