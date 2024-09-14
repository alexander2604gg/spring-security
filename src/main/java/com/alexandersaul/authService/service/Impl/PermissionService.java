package com.alexandersaul.authService.service.Impl;

import com.alexandersaul.authService.dto.PermissionDTO;
import com.alexandersaul.authService.mapper.IPermissionMapper;
import com.alexandersaul.authService.model.Permission;
import com.alexandersaul.authService.repository.IPermissionRepository;
import com.alexandersaul.authService.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;
    @Autowired
    private IPermissionMapper permissionMapper;

    @Override
    public List<PermissionDTO> getAllPermissions() {
        List<Permission> permissionList = (List<Permission>) permissionRepository.findAll();
        return permissionList.stream()
                .map(permissionMapper::toDTO)
                .toList();
    }

    @Override
    public PermissionDTO save(PermissionDTO permissionDTO) {
        Permission permission = permissionRepository.save(permissionMapper.toModel(permissionDTO));
        return permissionMapper.toDTO(permission);
    }

    @Override
    public PermissionDTO update(PermissionDTO permissionDTO, long id) {
        Optional<Permission> optionalPermission = permissionRepository.findById(id);
        if(optionalPermission.isPresent()){
            Permission permission = optionalPermission.get();
            permission.setPermissionName(permissionDTO.getPermissionName());
            return permissionMapper.toDTO(permission);
        } else {
            return null;
        }
    }

    @Override
    public PermissionDTO findById(long id) {
        Optional<Permission> permissionOptional = permissionRepository.findById(id);
        return permissionOptional.map(permission -> permissionMapper.toDTO(permission)).orElse(null);
    }

    @Override
    public void delete(long id) {
        permissionRepository.deleteById(id);
    }
}