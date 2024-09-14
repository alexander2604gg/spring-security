package com.alexandersaul.authService.mapper;

import com.alexandersaul.authService.dto.PermissionDTO;
import com.alexandersaul.authService.model.Permission;
import org.mapstruct.Mapper;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IPermissionMapper {

    PermissionDTO toDTO (Permission permission);
    Permission toModel (PermissionDTO permissionDTO);
    Set<PermissionDTO> toDTOs (Set<Permission> permissions);
    Set<Permission> toModels(Set<PermissionDTO> permissionDTOS);

}