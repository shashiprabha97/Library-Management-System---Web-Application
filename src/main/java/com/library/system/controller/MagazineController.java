package com.library.system.controller;

import com.library.system.models.MagazineDetails;
import com.library.system.models.MagazineRegistration;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.SearchReadings;
import com.library.system.services.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/magazine")
public class MagazineController {
    @Autowired
    private MagazineService magazineService;

    @GetMapping(value = "/{id}",produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity findMagazine(@PathVariable("id")String id,@RequestHeader(value = "token")String token){
        MagazineRegistration magazineDetails =  this.magazineService.findById(id,token);
        if(magazineDetails==null){
            RegistrationResponse registrationResponse = new RegistrationResponse("magazine not available",false);
            return new ResponseEntity(registrationResponse, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(magazineDetails, HttpStatus.OK);
        }
    }

    @PostMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity addMagazine(@RequestBody MagazineRegistration magReg, @RequestHeader(value = "token")String token){
        RegistrationResponse regRes = this.magazineService.saveMagazine(magReg,token);
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
    public ResponseEntity updeateMagazine(@PathVariable("id")String id,@RequestBody MagazineRegistration magReg,@RequestHeader(value = "token")String token){
        RegistrationResponse registrationResponse = this.magazineService.updateMagazine(magReg,token,id);
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
        RegistrationResponse registrationResponse = this.magazineService.deleteMagazine(token,id);
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
    public ResponseEntity getBookList(@RequestParam("type") String type, @RequestParam("value") String searchVal, @RequestHeader(value = "token",required = false)String token){
        List<SearchReadings> bookList = this.magazineService.searchMagazine(type,searchVal,token);
        if(bookList==null||bookList.isEmpty()){
            RegistrationResponse regRes = new RegistrationResponse("no any matches found",false);
            return new ResponseEntity(regRes,HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity(bookList,HttpStatus.OK);
        }
    }
}
