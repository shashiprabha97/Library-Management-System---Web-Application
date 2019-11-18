package com.library.system.controller;

import com.library.system.models.NewspaperDetails;
import com.library.system.models.NewspaperRegistration;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.SearchReadings;
import com.library.system.services.NewspaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/newspaper")
public class NewspaperController {
    @Autowired
    private NewspaperService newspaperService;

    @GetMapping(value = "/{id}",produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity findNewspaper(@PathVariable("id")String id, @RequestHeader(value = "token")String token){
        NewspaperDetails newspaperDetails =  this.newspaperService.findById(id,token);
        if(newspaperDetails==null){
            RegistrationResponse registrationResponse = new RegistrationResponse("news paper not available",false);
            return new ResponseEntity(registrationResponse, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(newspaperDetails, HttpStatus.OK);
        }
    }
    @PostMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity addNewspaper(@RequestBody NewspaperRegistration newReg, @RequestHeader(value = "token")String token){
        RegistrationResponse regRes = this.newspaperService.saveNewspaper(newReg,token);
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
    public ResponseEntity newspaperUpdate(@PathVariable("id")String id,@RequestBody NewspaperRegistration newReg,@RequestHeader(value = "token")String token){
        RegistrationResponse registrationResponse = this.newspaperService.updateMagazine(newReg,token,id);
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
    public ResponseEntity deleteNewspaper(@PathVariable("id")String id,@RequestHeader(value = "token")String token){
        RegistrationResponse registrationResponse = this.newspaperService.deleteMagazine(token,id);
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
        List<SearchReadings> bookList = this.newspaperService.searchNewsPaper(type,searchVal,token);
        if(bookList==null||bookList.isEmpty()){
            RegistrationResponse regRes = new RegistrationResponse("no any matches found",false);
            return new ResponseEntity(regRes,HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity(bookList,HttpStatus.OK);
        }
    }
}
