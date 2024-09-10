package com.ox.user.converters;

import com.ox.user.dto.RoleDTO;
import com.ox.user.dto.UserDTO;
import com.ox.user.entities.Role;
import com.ox.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final RoleConverter roleConverter;

    public UserDTO toDTO(User user) {
        UserDTO result = new UserDTO();
        result.setId(user.getId());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setMiddleName(user.getMiddleName());
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());
        result.setRoles(user.getRoles().stream().map(role -> {
            RoleDTO roleDTO = new RoleDTO();
            roleConverter.toDTO(role);
            return roleDTO;
        }).toList());
        return result;
    }

    public User fromDTO(UserDTO userDTO) {
        User result = new User();
        result.setId(userDTO.getId());
        result.setFirstName(userDTO.getFirstName());
        result.setLastName(userDTO.getLastName());
        result.setMiddleName(userDTO.getMiddleName());
        result.setEmail(userDTO.getEmail());
        result.setPassword(userDTO.getPassword());
        if (userDTO.getRoles() != null) {
            result.setRoles(userDTO.getRoles().stream().map(roleDTO -> {
                Role role = new Role();
                roleConverter.fromDTO(roleDTO);
                return role;
            }).toList());
        }

        return result;
    }
}
