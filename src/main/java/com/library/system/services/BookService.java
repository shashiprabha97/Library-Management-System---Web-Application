package com.library.system.services;

import com.library.system.models.BookDetails;
import com.library.system.models.BookRegistration;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.SearchReadings;

import java.util.List;

public interface BookService {
    BookDetails findById(String id,String token);
    RegistrationResponse saveBook(BookRegistration bkReg, String token);
    RegistrationResponse updateBook(BookRegistration bkReg,String token,String id);
    RegistrationResponse deleteBook(String token,String id);
    List<SearchReadings> searchBook(String type, String searchVal, String token);
}
