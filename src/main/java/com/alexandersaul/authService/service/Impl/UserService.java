package com.alexandersaul.authService.service.Impl;

import com.alexandersaul.authService.dto.UserDTO;
import com.alexandersaul.authService.mapper.IRoleMapper;
import com.alexandersaul.authService.mapper.IUserMapper;
import com.alexandersaul.authService.model.Role;
import com.alexandersaul.authService.model.UserSec;
import com.alexandersaul.authService.repository.IRoleRepository;
import com.alexandersaul.authService.repository.IUserRepository;
import com.alexandersaul.authService.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public List<UserDTO> findAllUsers() {
        List<UserSec> userList = (List<UserSec>) userRepository.findAll();
        return userList.stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO findById(long id) {
        Optional<UserSec> userOptional = userRepository.findById(id);
        return userOptional.map(user -> userMapper.toDTO(user)).orElse(null);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userDTO == null || userDTO.getRolesList() == null) {
            throw new IllegalArgumentException("UserDTO or its role list cannot be null");
        }

        userDTO.setPassword(encryptPassword(userDTO.getPassword()));
        UserSec user = userMapper.toModel(userDTO);
        Set<Role> roles = userDTO.getRolesList().stream()
                .map(roleDTO -> roleRepository.findById(roleDTO.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        user.setRolesList(roles);

        UserSec savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO update(UserDTO userSec, long id) {
        Optional<UserSec> userSecOptional = userRepository.findById(id);
        if (userSecOptional.isPresent()){
            UserSec newUser = userSecOptional.get();
            newUser.setUserName(userSec.getUserName());
            newUser.setPassword(userSec.getPassword());
            newUser.setRolesList(roleMapper.toModels(userSec.getRolesList()));
            return userMapper.toDTO(newUser);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}