package com.alexandersaul.authService.service;

import com.alexandersaul.authService.dto.RoleDTO;

import java.util.List;

public interface IRoleService {

    List<RoleDTO> findAllRoles();
    RoleDTO findById (long id);
    RoleDTO save (RoleDTO roleDTO);
    RoleDTO update (RoleDTO roleDTO , long id);
    void delete (long id);

}