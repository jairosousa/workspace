package com.example.UploadImage.controller;

import com.example.UploadImage.entity.User;
import com.example.UploadImage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/upload/post")
    public ResponseEntity<String> uploadProfilePic(@ModelAttribute User user) throws Exception {
        log.debug("Inside {} controller", "/users/upload/post");
        userService.updateProfilePicture(user.getUserId(), user.getProfilePicImageFile());
        return new ResponseEntity<>( "Upload Successful", HttpStatus.OK);
    }

    @GetMapping(value = { "/upload/get" }, produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getProfilePic(@RequestParam String emailId)  {
        log.debug("Inside {} controller", "/users/upload/get");
        User user = userService.getUserDetailsByEmailId(emailId);
        byte[] profilePicBytes = user.getProfilePicBytes();
        return new ResponseEntity<>(profilePicBytes, HttpStatus.OK);
    }

}
