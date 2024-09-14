package com.alexandersaul.authService.mapper;


import com.alexandersaul.authService.dto.RoleDTO;
import com.alexandersaul.authService.model.Role;
import org.mapstruct.Mapper;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

    RoleDTO toDTO (Role role);
    Role toModel (RoleDTO role);
    Set<Role> toModels (Set<RoleDTO> roleDTOS);
    Set<RoleDTO> toDTOs (Set<Role> roles);

}