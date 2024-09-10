package com.ox.user.converters;

import com.ox.user.dto.RoleDTO;
import com.ox.user.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public RoleDTO toDTO(Role role) {
        RoleDTO result = new RoleDTO();
        result.setId(role.getId());
        result.setName(role.getName());
        return result;
    }

    public Role fromDTO(RoleDTO roleDTO) {
        Role result = new Role();
        result.setId(roleDTO.getId());
        result.setName(roleDTO.getName());
        return result;
    }
}
