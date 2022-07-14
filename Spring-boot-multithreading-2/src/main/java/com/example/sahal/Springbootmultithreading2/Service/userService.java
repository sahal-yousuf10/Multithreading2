package com.example.sahal.Springbootmultithreading2.Service;

import com.example.sahal.Springbootmultithreading2.Model.User;
import com.example.sahal.Springbootmultithreading2.Repository.userRepository;
import com.example.sahal.Springbootmultithreading2.dto.UserDto;
import com.example.sahal.Springbootmultithreading2.mapper.userMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class userService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private userMapper userMapper;


    @Async
    public String saveUsersThroughFile(MultipartFile file) throws Exception{
        List<User> userList = parseCSVFile(file);
        log.info("Saving list of users of size "+userList.size()+" "+Thread.currentThread().getName());
        userRepository.saveAll(userList);
        return "Data saved successfully!";
    }

    @Async
    public CompletableFuture<List<User>> findAllUsers() {
        log.info("Getting list of users by "+Thread.currentThread().getName());
        List<User> userList = userRepository.findAll();
        return CompletableFuture.completedFuture(userList);
    }

    @Async
    public CompletableFuture<User> findAllUsersByThread(long id) {
        //List<User> userList = new ArrayList<>();
            User user = userRepository.findById(id).get();
            //userList.add(user);
            log.info("finding user by thread "+Thread.currentThread().getName());

        return CompletableFuture.completedFuture(user);
    }

    private List<User> parseCSVFile(MultipartFile file) throws Exception{
        final List<User> userList = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final User user = new User();
                    user.setFirstName(data[0]);
                    user.setLastName(data[1]);
                    user.setEmail(data[2]);
                    user.setGender(data[3]);
                    user.setCompanyName(data[4]);
                    //user.setCity(data[5]);
                    user.setJobTitle(data[5]);
                    user.setActive(true);
                    userList.add(user);
                }
                return userList;
            }
        }
        catch (Exception ex){
            log.error("Failed to parse CSV file "+ex);
            throw ex;
        }
    }

    @Async
    public CompletableFuture<UserDto> findUserById(long id) throws Exception {
        User user = userRepository.findById(id)
                .filter(User::isActive)
                .orElseThrow(()-> new Exception("User not found with id "+id));
        UserDto userDto = userMapper.entityToDto(user);
        return CompletableFuture.completedFuture(userDto);
    }

    @Async
    public String saveUser(UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        user.setActive(true);
        String result = null;
        try {
            userRepository.save(user);
            result = "User created successfully";
        }
        catch (Exception ex){
            result = "Exception caught "+ex;
            throw ex;
        }
        finally {
            return result;
        }
    }

    @Async
    public String updateUser(long id, UserDto userDto) {
        boolean isUserAlreadyPresent = userRepository.findById(id)
                .filter(User::isActive)
                .isPresent();
        User updatedUser = userMapper.dtoToEntity(userDto);
        updatedUser.setActive(true);
        String result = null;
        if(isUserAlreadyPresent){
            updatedUser.setId(id);
            result = "User with id "+id+" updated successfully";
        }
        else {
            result = "User with id "+id+" does not exist, therefore new user is created";
        }
        userRepository.save(updatedUser);
        return result;
    }
}
