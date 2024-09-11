package com.ox.user.controllers;

import com.ox.user.dto.ClientDTO;
import com.ox.user.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @Operation(description = "Search client by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @GetMapping("/find-by-name/{name}")
    public List<ClientDTO> findByName(@PathVariable String name) {
        return clientService.searchByName(name);
    }

    @Operation(description = "Search client by address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @GetMapping("/find-by-address/{address}")
    public List<ClientDTO> findByAddress(@PathVariable String address) {
        return clientService.searchByAddress(address);
    }

    @Operation(description = "Find all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @GetMapping("/find-all")
    public List<ClientDTO> findAllClients() {
        return clientService.findAll();
    }
}