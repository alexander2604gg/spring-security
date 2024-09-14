package com.alexandersaul.authService.service.Impl;

import com.alexandersaul.authService.dto.RoleDTO;
import com.alexandersaul.authService.mapper.IPermissionMapper;
import com.alexandersaul.authService.mapper.IRoleMapper;
import com.alexandersaul.authService.model.Permission;
import com.alexandersaul.authService.model.Role;
import com.alexandersaul.authService.repository.IPermissionRepository;
import com.alexandersaul.authService.repository.IRoleRepository;
import com.alexandersaul.authService.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private IRoleMapper roleMapper;
    @Autowired
    private IPermissionMapper permissionMapper;

    @Override
    public List<RoleDTO> findAllRoles() {
        List<Role> roleList = (List<Role>) roleRepository.findAll();
        return roleList.stream()
                .map(roleMapper::toDTO)
                .toList();
    }

    @Override
    public RoleDTO findById(long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.map(role -> roleMapper.toDTO(role)).orElse(null);
    }

    public RoleDTO save(RoleDTO roleDTO) {

        if (roleDTO == null || roleDTO.getPermissionList() == null) {
            throw new IllegalArgumentException("RoleDTO or its permission list cannot be null");
        }

        Role role = roleMapper.toModel(roleDTO);
        Set<Permission> permissions = roleDTO.getPermissionList().stream()
                .map(permissionDTO -> permissionRepository.findById(permissionDTO.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        role.setPermissionList(permissions);

        Role savedRole = roleRepository.save(role);

        return roleMapper.toDTO(savedRole);
    }


    @Override
    public RoleDTO update(RoleDTO roleDTO, long id) {

        Optional<Role> optionalRole = roleRepository.findById(id);
        if(optionalRole.isPresent()){
            Role role = optionalRole.get();
            role.setRole(roleDTO.getRole());
            role.setPermissionList(permissionMapper.toModels(roleDTO.getPermissionList()));
            role = roleRepository.save(role); // Save the updated entity
            return roleMapper.toDTO(role);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        roleRepository.deleteById(id);
    }
}