package com.alexandersaul.authService.repository;

import com.alexandersaul.authService.model.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends CrudRepository<Permission,Long> {
}