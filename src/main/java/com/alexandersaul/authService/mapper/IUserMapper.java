package com.alexandersaul.authService.mapper;

import com.alexandersaul.authService.dto.UserDTO;
import com.alexandersaul.authService.model.UserSec;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserDTO toDTO (UserSec userSec);
    UserSec toModel (UserDTO userDTO);
}
