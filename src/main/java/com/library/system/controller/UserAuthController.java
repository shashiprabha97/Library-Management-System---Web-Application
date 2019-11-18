package com.library.system.controller;

import com.library.system.models.ForeignUser;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.TokenResponse;
import com.library.system.Entity.User;
import com.library.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/user")
public class UserAuthController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", produces={
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
    })
    public ResponseEntity login(@RequestBody User user){
        TokenResponse token = userService.authenticateUser(user.getUsername(),user.getPassword());
        if(token == null){
            token = new TokenResponse("0","Invalid Credentials");
            return new ResponseEntity(token, HttpStatus.NOT_FOUND);
        }else if(!token.getStatus()){
            return new ResponseEntity(token, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(token, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/register", produces={
           MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
    })
    public ResponseEntity register(@RequestBody User user){
        RegistrationResponse rRes = this.userService.registerUser(user);
        ResponseEntity re = rRes.getStatus()?new ResponseEntity(rRes,HttpStatus.ACCEPTED):
                                                new ResponseEntity(rRes,HttpStatus.NOT_ACCEPTABLE);
        return re;//responseEntity
    }

    @GetMapping(value = "/foreign/reg/list",produces={
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity listOfForeignToRegister(@RequestHeader("token")String token){
        List<ForeignUser> users = this.userService.unregisteredForeign(token);
        if(users == null){
            return new ResponseEntity(new RegistrationResponse("not permission to access this data",false),HttpStatus.BAD_REQUEST);
        }else if(users.isEmpty()){
            return new ResponseEntity(new RegistrationResponse("no any unregistered user found",false),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity(users,HttpStatus.OK);
        }
    }
}
