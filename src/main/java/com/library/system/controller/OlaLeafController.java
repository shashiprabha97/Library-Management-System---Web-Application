package com.library.system.controller;

import com.library.system.models.*;
import com.library.system.services.NewspaperService;
import com.library.system.services.OlaLeafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/olaleaf")
public class OlaLeafController {
    @Autowired
    private OlaLeafService olaLeafService;

    @GetMapping(value = "/{id}",produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity findNewspaper(@PathVariable("id")String id, @RequestHeader(value = "token")String token){
        OlaLeafDetails olaLeafDetails =  this.olaLeafService.findById(id,token);
        if(olaLeafDetails==null){
            RegistrationResponse registrationResponse = new RegistrationResponse("ola leaf manuscript not available",false);
            return new ResponseEntity(registrationResponse, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(olaLeafDetails, HttpStatus.OK);
        }
    }
    @PostMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity addOlaLeaf(@RequestBody OlaLeafRegistration olaReg, @RequestHeader(value = "token")String token){
        RegistrationResponse regRes = this.olaLeafService.saveOlaLeaf(olaReg,token);
        if(regRes!=null&&regRes.getStatus()){
            return new ResponseEntity(regRes,HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity(regRes,HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity olaLeafUpdate(@PathVariable("id")String id,@RequestBody OlaLeafRegistration olaReg,@RequestHeader(value = "token")String token){
        RegistrationResponse registrationResponse = this.olaLeafService.updateOlaLeaf(olaReg,token,id);
        if(registrationResponse.getStatus()){
            return new ResponseEntity(registrationResponse,HttpStatus.OK);
        }else{
            return new ResponseEntity(registrationResponse,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}",  produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity deleteOlaLeaf(@PathVariable("id")String id,@RequestHeader(value = "token")String token){
        RegistrationResponse registrationResponse = this.olaLeafService.deleteOlaLeaf(token,id);
        if(registrationResponse.getStatus()){
            return new ResponseEntity(registrationResponse,HttpStatus.OK);
        }else{
            return new ResponseEntity(registrationResponse,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/search", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity getNewspaperList(@RequestParam("type") String type, @RequestParam("value") String searchVal, @RequestHeader(value = "token",required = false)String token){
        List<SearchReadings> bookList = this.olaLeafService.searchOlaLeaf(type,searchVal,token);
        if(bookList==null||bookList.isEmpty()){
            RegistrationResponse regRes = new RegistrationResponse("no any matches found",false);
            return new ResponseEntity(regRes,HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity(bookList,HttpStatus.OK);
        }
    }
}
