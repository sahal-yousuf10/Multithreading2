package com.example.sahal.Springbootmultithreading2.mapper;

import com.example.sahal.Springbootmultithreading2.Model.User;
import com.example.sahal.Springbootmultithreading2.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-14T21:29:54+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class userMapperImpl implements userMapper {

    @Override
    public UserDto entityToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setEmail( user.getEmail() );
        userDto.setGender( user.getGender() );
        userDto.setCompanyName( user.getCompanyName() );
        userDto.setJobTitle( user.getJobTitle() );

        return userDto;
    }

    @Override
    public User dtoToEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setEmail( userDto.getEmail() );
        user.setGender( userDto.getGender() );
        user.setCompanyName( userDto.getCompanyName() );
        user.setJobTitle( userDto.getJobTitle() );

        return user;
    }

    @Override
    public List<UserDto> entityToDto(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userList.size() );
        for ( User user : userList ) {
            list.add( entityToDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> dtoToEntity(List<UserDto> userDtoList) {
        if ( userDtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDtoList.size() );
        for ( UserDto userDto : userDtoList ) {
            list.add( dtoToEntity( userDto ) );
        }

        return list;
    }
}
