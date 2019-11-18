package com.library.system.services;

import com.library.system.Entity.Librarian;
import com.library.system.models.LibrarianData;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.TokenResponse;

import java.util.List;

public interface LibrarianService {
    TokenResponse authenticateLibrarian(String username, String password);
    RegistrationResponse addLibrarian(String token, Librarian librarian);
    RegistrationResponse foreignerRegister(String token,String id);
    List<LibrarianData> getList(String token);
    RegistrationResponse deleteLibrarian(String token, String id);
}
