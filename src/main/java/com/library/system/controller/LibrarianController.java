package com.library.system.controller;

import com.library.system.Entity.Librarian;
import com.library.system.models.LibrarianData;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.TokenResponse;
import com.library.system.services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/librarian")
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;

    @PostMapping(value = "/login", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity login(@RequestBody Librarian librarian){
        TokenResponse tr = this.librarianService.authenticateLibrarian(librarian.getUsername(),librarian.getPassword());
        if(tr.getStatus()){
            return new ResponseEntity(tr,HttpStatus.OK);
        }else {
            return new ResponseEntity(tr,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity addLibrarian(@RequestHeader String token, @RequestBody Librarian librarian){
            RegistrationResponse regRes = librarianService.addLibrarian(token,librarian);
        return regRes.getStatus()?new ResponseEntity(regRes, HttpStatus.ACCEPTED):new ResponseEntity(regRes, HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping(value = "/register/foreigner/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity foriegnerRegister(@RequestHeader(value = "token")String token, @PathVariable("id")String id){
        System.out.println(id);
        RegistrationResponse regRes = this.librarianService.foreignerRegister(token,id);
        if (regRes.getStatus()){
            return new ResponseEntity(regRes,HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity(regRes,HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @GetMapping(value = "/list", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity librarianList(@RequestHeader String token){
        List<LibrarianData> libList = this.librarianService.getList(token);
        if (libList.isEmpty()){
            RegistrationResponse rr = new RegistrationResponse("no any user found",false);
            return new ResponseEntity(rr,HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(libList,HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity deleteLibrarian(@PathVariable("id") String id, @RequestHeader(value = "token") String token){
        RegistrationResponse rr = this.librarianService.deleteLibrarian(token, id);
        if (rr.getStatus()){
            return new ResponseEntity(rr,HttpStatus.OK);
        }else{
            return new ResponseEntity(rr,HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
