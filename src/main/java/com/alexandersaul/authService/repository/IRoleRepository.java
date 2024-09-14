package com.alexandersaul.authService.repository;

import com.alexandersaul.authService.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends CrudRepository<Role,Long> {
}