package com.example.sahal.Springbootmultithreading2.mapper;

import com.example.sahal.Springbootmultithreading2.Model.User;
import com.example.sahal.Springbootmultithreading2.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface userMapper {

    UserDto entityToDto(User user);
    User dtoToEntity(UserDto userDto);
    List<UserDto> entityToDto(List<User> userList);
    List<User> dtoToEntity(List<UserDto> userDtoList);
}
