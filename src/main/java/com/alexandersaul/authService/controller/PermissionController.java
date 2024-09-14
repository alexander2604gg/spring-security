package com.alexandersaul.authService.controller;

import com.alexandersaul.authService.dto.PermissionDTO;
import com.alexandersaul.authService.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/resource")
    public ResponseEntity<List<PermissionDTO>> getAllPermissions () {
        List<PermissionDTO> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<PermissionDTO> getPermissionById (@PathVariable long id){
        PermissionDTO permission = permissionService.findById(id);
        return ResponseEntity.ok(permission);
    }

    @PostMapping ("/resource")
    public ResponseEntity<PermissionDTO> savePermission (@RequestBody PermissionDTO permissionDTO) {

        if(permissionDTO == null){
            return ResponseEntity.badRequest().build();
        }

        PermissionDTO newPermissionDTO = permissionService.save(permissionDTO);

        if(newPermissionDTO != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(newPermissionDTO);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}