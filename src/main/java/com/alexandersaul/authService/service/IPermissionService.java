package com.alexandersaul.authService.service;

import com.alexandersaul.authService.dto.PermissionDTO;

import java.util.List;

public interface IPermissionService {

    List<PermissionDTO> getAllPermissions();
    PermissionDTO save(PermissionDTO permissionDTO);
    PermissionDTO update (PermissionDTO permissionDTO , long id);
    PermissionDTO findById(long id);
    void delete(long id);

}
