package com.library.system.controller;


import com.library.system.Entity.User;
import com.library.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public ResponseEntity testReply(){
        return new ResponseEntity("\"Success\"", HttpStatus.OK);
    }

    @GetMapping(value = "/user/all", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public List<User> allUsers(){
        List<User> users= userRepository.findAll();
        return users;
    }

}
