package com.ox.user.controllers;

import com.ox.user.dto.ContactDTO;
import com.ox.user.services.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public void deleteContact(@PathVariable Long id) {
        contactService.delete(id);
    }

    @Operation(description = "Find all contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })

    @GetMapping("/find-all")
    public List<ContactDTO> findAllContacts() {
        return contactService.findAll();
    }

    @Operation(description = "Search contact by lastname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @GetMapping("/find-by-lastname/{lastname}")
    public List<ContactDTO> findByFirstName(@PathVariable String lastname) {
        return contactService.searchByLastName(lastname);
    }

    @Operation(description = "Search contact by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @GetMapping("/find-by-email/{email}")
    public List<ContactDTO> findByAddress(@PathVariable String email) {
        return contactService.searchByEmail(email);
    }
}
