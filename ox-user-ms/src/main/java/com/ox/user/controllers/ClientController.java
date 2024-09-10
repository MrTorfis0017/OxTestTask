package com.ox.user.controllers;

import com.ox.user.dto.ClientDTO;
import com.ox.user.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @Operation(description = "Creating client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @PostMapping("/create")
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.create(clientDTO);
    }

    @Operation(description = "Update client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @PutMapping("/update")
    public ClientDTO updateClient(@RequestBody ClientDTO clientDTO) {
        return clientService.update(clientDTO);
    }

    @Operation(description = "Get client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @GetMapping("/get/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.get(id);
    }

    @Operation(description = "Delete client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }
}