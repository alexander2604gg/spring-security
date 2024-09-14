package com.alexandersaul.authService.repository;

import com.alexandersaul.authService.model.UserSec;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<UserSec,Long> {

    Optional<UserSec> findUserSecByUserName (String userName);

}