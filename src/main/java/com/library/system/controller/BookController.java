package com.library.system.controller;

import com.library.system.models.*;
import com.library.system.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity bookSearch(@PathVariable("id")String id, @RequestHeader(value = "token",required = false)String token){
        BookDetails bookDetails = bookService.findById(id,token);
        if (bookDetails != null){
            return new ResponseEntity(bookDetails,HttpStatus.OK);
        }else{
            return new ResponseEntity(new Responses("Book item not accessible /Or not available"),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity bookAdding(@RequestBody BookRegistration bkReg,@RequestHeader(value = "token")String token){

        RegistrationResponse regRes = this.bookService.saveBook(bkReg,token);
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
    public ResponseEntity bookUpdate(@PathVariable("id")String id,@RequestBody BookRegistration bkReg,@RequestHeader(value = "token")String token){
        RegistrationResponse registrationResponse = this.bookService.updateBook(bkReg,token,id);
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
    public ResponseEntity deleteBook(@PathVariable("id")String id,@RequestHeader(value = "token")String token){
        RegistrationResponse registrationResponse = this.bookService.deleteBook(token,id);
        if(registrationResponse.getStatus()){
            return new ResponseEntity(registrationResponse,HttpStatus.OK);
        }else{
            return new ResponseEntity(registrationResponse,HttpStatus.NOT_ACCEPTABLE);
        }
    }
    //get search data
    @GetMapping(value = "/search", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public List<SearchReadings> getBookList(@RequestParam("type") String type,@RequestParam("value") String searchVal,@RequestHeader(value = "token",required = false)String token){
        List<SearchReadings> bookList = this.bookService.searchBook(type,searchVal,token);
        return  bookList;
    }

}
