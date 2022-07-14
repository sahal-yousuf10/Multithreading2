package com.example.sahal.Springbootmultithreading2.Controller;

import com.example.sahal.Springbootmultithreading2.Model.User;
import com.example.sahal.Springbootmultithreading2.Service.userService;
import com.example.sahal.Springbootmultithreading2.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import static com.example.sahal.Springbootmultithreading2.constant.Constant.*;

@RestController
public class userController {
    
    @Autowired
    private userService userService;

    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity<String> saveUsersThroughFile(@Valid @RequestParam(value = "files")MultipartFile[] files) throws Exception{
        String result = null;
        for (MultipartFile file : files){
            result = userService.saveUsersThroughFile(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto){
        String result = userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@Positive(message = ID_SHOULD_BE_POSITIVE_ERROR_MESSAGE) @PathVariable long id, @Valid @RequestBody UserDto userDto){
        String result = userService.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(value = "/users")
    public CompletableFuture<ResponseEntity> findAllUsers(){
        //CompletableFuture<List<User>> userList = userService.findAllUsers();
        //return (CompletableFuture<ResponseEntity>) ResponseEntity.ok();
        return userService.findAllUsers().thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/user/{id}")
    public CompletableFuture<ResponseEntity> findUserById(@Positive(message = ID_SHOULD_BE_POSITIVE_ERROR_MESSAGE) @PathVariable long id) throws Exception {
        CompletableFuture<UserDto> userDto = null;
        try {
            userDto = userService.findUserById(id);
        }
        catch (Exception e){
            throw e;
        }
        //return CompletableFuture<ResponseEntity.status(HttpStatus.FOUND).body(userDto)>;
        return userDto.thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "users/thread")
    public ResponseEntity<List<CompletableFuture<User>>> findAllUsersByThread(){
        List<CompletableFuture<User>> listCompletableFuture = null;
        CompletableFuture<User> userCompletableFuture;
        for (long i =1 ; i<=3000; i++) {
            User user = new User();
            userCompletableFuture = userService.findAllUsersByThread(i);
            listCompletableFuture.add(userCompletableFuture);
            //return userService.findAllUsersByThread().thenApply(ResponseEntity::ok);
        }
        return new ResponseEntity<List<CompletableFuture<User>>>(listCompletableFuture, HttpStatus.OK);
    }
}
