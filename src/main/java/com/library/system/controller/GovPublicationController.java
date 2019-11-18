package com.library.system.controller;

import com.library.system.Entity.GovPublish;
import com.library.system.models.*;
import com.library.system.services.GovPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/govpublish")
public class GovPublicationController {
    @Autowired
    private GovPublishService govPublishService;

    @GetMapping(value = "/{id}",produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity findMagazine(@PathVariable("id")String id, @RequestHeader(value = "token")String token){
        GovPublishDetails govPublishDetails =  this.govPublishService.findById(id,token);

        if(govPublishDetails==null){
            RegistrationResponse registrationResponse = new RegistrationResponse("magazine not available",false);
            return new ResponseEntity(registrationResponse, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(govPublishDetails, HttpStatus.OK);
        }
    }

    @PostMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity addMagazine(@RequestBody GovPublishRegistration govReg, @RequestHeader(value = "token")String token){
        RegistrationResponse regRes = this.govPublishService.saveGovPublish(govReg,token);
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
    public ResponseEntity bookMagazine(@PathVariable("id")String id, @RequestBody GovPublishRegistration govReg, @RequestHeader(value = "token")String token){
        RegistrationResponse registrationResponse = this.govPublishService.updateGovPublish(govReg,token,id);
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
    public ResponseEntity deleteMagazine(@PathVariable("id")String id,@RequestHeader(value = "token")String token){
        RegistrationResponse registrationResponse = this.govPublishService.deleteGovPublish(token,id);
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
        List<SearchReadings> bookList = this.govPublishService.searchGovPublish(type,searchVal,token);
        if(bookList==null||bookList.isEmpty()){
            RegistrationResponse regRes = new RegistrationResponse("no any matches found",false);
            return new ResponseEntity(regRes,HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity(bookList,HttpStatus.OK);
        }
    }

}
