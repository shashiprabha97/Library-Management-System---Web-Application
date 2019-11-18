package com.library.system.services.serviceImple;

import com.library.system.Entity.Authors;
import com.library.system.Entity.Book;
import com.library.system.Entity.Readings;
import com.library.system.data.AccessData;
import com.library.system.models.*;
import com.library.system.repositories.AuthorRepository;
import com.library.system.repositories.BookRepository;
import com.library.system.repositories.ReadingsRepository;
import com.library.system.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    ReadingsRepository readingsRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    //access controll by user
    public BookDetails findById(String id ,String token){
        String userType[] = token.split("\\.");
        BookDetails bookDetails = null;
        Optional<Readings> optRead;
        System.out.println(Arrays.toString(userType));

        if(userType[AccessData.USERTYPE] != null && (userType[AccessData.USERTYPE].equals(AccessData.SRILANKA)
                    ||userType[AccessData.USERTYPE].equals(AccessData.NORMAL)||userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)))  {
            optRead = this.readingsRepository.findById(id);
        }else {
            optRead = this.readingsRepository.findById(AccessData.PUBLIC,id);
        }
        if (optRead.isPresent()) {
            Readings read = optRead.get();

            Optional<Book> optional = this.bookRepository.findById(id);
            Book book = optional.get();


            List<Authors> optAuthors = this.authorRepository.findAllById(id);
            bookDetails = new BookDetails(read.getId(),read.getCategory(), read.getTitle(), read.getLanguage(), book.getPublisher(),
                        book.getPrice(), read.getYear(), book.getIsbn(), optAuthors,read.getState());
        }

        return bookDetails;
    }

    public RegistrationResponse saveBook(BookRegistration bkReg,String token){
        String []tokenData = (token!=null)?token.split("\\."):null;
        RegistrationResponse regResponse = null;
        try {
            Optional<Readings> seachBook = this.readingsRepository.findById(bkReg.getId());
            if(!seachBook.isPresent()){
                Readings readings = new Readings(bkReg.getId(), bkReg.getCategory(), bkReg.getTitle(), bkReg.getLanguage(), bkReg.getState(), AccessData.BOOK, tokenData[AccessData.USERNAME],bkReg.getYear());
                Book book = new Book(bkReg.getId(), bkReg.getPublisher(), bkReg.getPrice(), bkReg.getYear(), bkReg.getIsbn());
                List<Authors> authors = bkReg.getAuthors();

                for (Authors author : authors) {
                    author.setReadingsId(bkReg.getId());
                }

                if (tokenData[AccessData.USERTYPE].equals(AccessData.PRINCIPAL) || tokenData[AccessData.USERTYPE].equals(AccessData.NORMAL)) {

                    this.readingsRepository.save(readings);
                    this.bookRepository.save(book);
                    this.authorRepository.saveAll(authors);

                    regResponse = new RegistrationResponse("New book is added successfully", true);

                } else {
                    regResponse = new RegistrationResponse("You have no authorized access", false);
                }
            }else {
                regResponse = new RegistrationResponse("Book id already exist", false);
            }


        }catch (NullPointerException ex ){
            regResponse = new RegistrationResponse("Access token is not valid", false);
        }catch (ArrayIndexOutOfBoundsException ex){
            regResponse = new RegistrationResponse("Access token is not valid", false);
        }
        return regResponse;
    }

    public RegistrationResponse updateBook(BookRegistration bkReg,String token,String id){
        String []tokenSplit = (token!=null)?token.split("\\."):null;
        RegistrationResponse registrationResponse = null;
        Optional<Readings> optRead = this.readingsRepository.findById(id);
        try {
            if(tokenSplit[AccessData.USERTYPE].equals(AccessData.NORMAL) || tokenSplit[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)) {

                if (optRead.isPresent()) {
                    Readings readings = optRead.get();
                    Book book = this.bookRepository.findById(id).get();
                    List<Authors> authors = this.authorRepository.findAllById(id);

                    readings.setLanguage(bkReg.getLanguage());
                    readings.setCategory(bkReg.getCategory());
                    readings.setYear(bkReg.getYear());

                    if (bkReg.getState() != readings.getState() && !tokenSplit[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)) {
                        return new RegistrationResponse("User has no perssion to change Book State", false);
                    } else {
                        readings.setState(bkReg.getState());
                    }

                    book.setPrice(bkReg.getPrice());
                    book.setIsbn(bkReg.getIsbn());
                    book.setPublisher(bkReg.getPublisher());
                    book.setYear(bkReg.getYear());

                    if(bkReg.getAuthors() != null){
                        this.authorRepository.deleteAll(authors);

                        bkReg.getAuthors().forEach(v->{
                            v.setReadingsId(id);
                        });

                        this.authorRepository.saveAll(bkReg.getAuthors());
                    }


                    this.readingsRepository.save(readings);
                    this.bookRepository.save(book);
                    registrationResponse = new RegistrationResponse("Successfully updated",true);
                }else{
                    registrationResponse = new RegistrationResponse("selected book is not available",false);
                }
            }else {
                registrationResponse = new RegistrationResponse("not authorize access to update data",false);
            }
        }catch (NullPointerException ex){
            ex.printStackTrace();
            registrationResponse = new RegistrationResponse("User token is not valid",false);
        }
        return registrationResponse;
    }

    public RegistrationResponse deleteBook(String token,String id){
        String []tokenSplit = (token!=null)?token.split("\\."):null;
        RegistrationResponse registrationResponse = null;

        try{
            if(tokenSplit[AccessData.USERTYPE].equals(AccessData.NORMAL) || tokenSplit[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)){
                Optional<Readings> optRead = this.readingsRepository.findById(id);
                if(optRead.isPresent()){
                    Readings readings = optRead.get();
                    Book book = this.bookRepository.findById(id).get();
                    List<Authors> authors = this.authorRepository.findAllById(id);

                    for(Authors author: authors){
                        this.authorRepository.delete(author);
                    }
                    this.bookRepository.delete(book);
                    this.readingsRepository.delete(readings);
                    registrationResponse = new RegistrationResponse("Successfully Deleted", true);
                }else {
                    registrationResponse = new RegistrationResponse("no book found", false);
                }
            }else{
                registrationResponse = new RegistrationResponse("no authorized access", false);
            }

        }catch (NullPointerException ex ){
            registrationResponse = new RegistrationResponse("token miss match", false);
        }catch (ArrayIndexOutOfBoundsException ex){
            registrationResponse = new RegistrationResponse("token miss match", false);
        }
        return registrationResponse;
    }

    //could be able to search book by id,category,title,language
    public List<SearchReadings> searchBook(String type,String searchVal,String token){
        String userType[] = token!=null?token.split("\\."):null;
        List<Readings> readingsList = null;
        List<SearchReadings> searchReadings = new ArrayList<>();

        switch (type){
            case AccessData.YEAR:{
                readingsList = this.readingsRepository.findByYear(searchVal,AccessData.BOOK,AccessData.PUBLIC);
                if(userType!=null&&(userType[AccessData.USERTYPE].equals(AccessData.SRILANKA)||userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)||userType[AccessData.USERTYPE].equals(AccessData.NORMAL))){
                    List<Readings> optionalList = this.readingsRepository.findByYear(searchVal,AccessData.BOOK,AccessData.RARE);
                    readingsList.addAll(optionalList);
                }
                break;
            }
            case AccessData.CATEGORY:{
                readingsList = this.readingsRepository.findByCategory(searchVal,AccessData.BOOK,AccessData.PUBLIC);
                if(userType!=null&&(userType[AccessData.USERTYPE].equals(AccessData.SRILANKA)||userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)||userType[AccessData.USERTYPE].equals(AccessData.NORMAL))){
                    List<Readings> optionalList = this.readingsRepository.findByCategory(searchVal,AccessData.BOOK,AccessData.RARE);
                    readingsList.addAll(optionalList);
                }
                break;
            }
            case AccessData.TITLE:{
                readingsList = this.readingsRepository.findByTitle(searchVal,AccessData.BOOK,AccessData.PUBLIC);
                if(userType!=null&&(userType[AccessData.USERTYPE].equals(AccessData.SRILANKA)||userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)||userType[AccessData.USERTYPE].equals(AccessData.NORMAL))){
                    List<Readings> optionalList = this.readingsRepository.findByTitle(searchVal,AccessData.BOOK,AccessData.RARE);
                    readingsList.addAll(optionalList);

                }
                break;
            }
            case AccessData.LANGUAGE:{
                readingsList = this.readingsRepository.findByLanguage(searchVal,AccessData.BOOK,AccessData.PUBLIC);
                if(userType!=null&&(userType[AccessData.USERTYPE].equals(AccessData.SRILANKA)||userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)||userType[AccessData.USERTYPE].equals(AccessData.NORMAL))){
                    List<Readings> optionalList = this.readingsRepository.findByLanguage(searchVal,AccessData.BOOK,AccessData.RARE);
                    readingsList.addAll(optionalList);
                }
                break;
            }
        }

        if(readingsList!=null){
            readingsList.forEach(v->{
                SearchReadings sr = new SearchReadings(v.getId(),v.getCategory(),v.getTitle(),v.getType());
                searchReadings.add(sr);
            });
        }

        return searchReadings;
    }
}
