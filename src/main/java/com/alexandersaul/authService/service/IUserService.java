package com.alexandersaul.authService.service;

import com.alexandersaul.authService.dto.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> findAllUsers();
    UserDTO findById (long id);
    UserDTO save (UserDTO userDTO);
    UserDTO update (UserDTO userSec , long id);
    void delete (long id);
    public String encryptPassword(String password);
}