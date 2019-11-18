package com.library.system.services.serviceImple;

import com.library.system.Entity.Librarian;
import com.library.system.Entity.User;
import com.library.system.data.AccessData;
import com.library.system.models.LibrarianData;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.TokenResponse;
import com.library.system.repositories.LibrarianRepository;
import com.library.system.repositories.UserRepository;
import com.library.system.services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private UserRepository userRepository;

    //librarian authenticate
    public TokenResponse authenticateLibrarian(String username, String password) {
       Optional<Librarian> optLibrarian = librarianRepository.findById(username);
        TokenResponse tokenResponse = null;
        if(optLibrarian.isPresent()){
            Librarian librarian = optLibrarian.get();
            //checking the availability of the user
            if (librarian.getPassword().equals(password)) {
                tokenResponse = new TokenResponse(AccessData.tokenGenerator(librarian),"authenticate success",true);
            }else {
                tokenResponse = new TokenResponse("","wrong password",false);
            }

        }else {
            tokenResponse = new TokenResponse("","invalid username",false);
        }
        return tokenResponse;
    }

    //add librarian
    public RegistrationResponse addLibrarian(String token, Librarian librarian){
        String []tokenData = (token!=null)?token.split("\\."):null;
        RegistrationResponse registrationResponse = null;
        if(tokenData[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)){
            librarian.setState(AccessData.NORMAL);
            this.librarianRepository.save(librarian);

            registrationResponse = new RegistrationResponse("successfully registered",true);
        }else{
            registrationResponse = new RegistrationResponse("no any permission to register librarian",false);
        }
        return registrationResponse;
    }

    //giving accesss to Forign
    public RegistrationResponse foreignerRegister(String token,String id){
        String []tokenData = (token!=null)?token.split("\\."):null;
        RegistrationResponse registrationResponse = null;
        Optional<User> optOser = this.userRepository.findById(id);
        System.out.println(token);

        if (tokenData[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)){
            if (optOser.isPresent()){
                User user = optOser.get();
                if (user.getIsReg()==0){
                    user.setIsReg(1);
                    this.userRepository.save(user);
                    registrationResponse = new RegistrationResponse("succussfully registered",true);
                }else{
                    registrationResponse = new RegistrationResponse("already registered",false);
                }
            }else {
                registrationResponse = new RegistrationResponse("not found",false);
            }
        }else{
            registrationResponse = new RegistrationResponse("no permission to registere a foriegner",false);
        }

        return registrationResponse;
    }

    @Override
    public List<LibrarianData> getList(String token) {
        String []tokenData = (token!=null)?token.split("\\."):null;
        List<LibrarianData> responseList = new ArrayList<>();

        if (tokenData!=null&& tokenData[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)){
            List<Librarian> librarianList = this.librarianRepository.findAll();
            librarianList.forEach(v->{
                if(!v.getState().equals(AccessData.PRINCIPAL)){
                    LibrarianData ld = new LibrarianData(v.getUsername(),v.getFirstName(),v.getLastName());
                    responseList.add((ld));
                }
            });
        }
        return responseList;
    }

    public RegistrationResponse deleteLibrarian(String token, String id){
        String []tokenData = (token!=null)?token.split("\\."):null;
        Optional<Librarian> optLib= this.librarianRepository.findById(id);// find optional librarian
        RegistrationResponse rr = null;

        if(tokenData!=null && tokenData[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)){
            if(optLib.isPresent()){
                Librarian librarian = optLib.get();
                this.librarianRepository.delete(librarian);
                rr = new RegistrationResponse("successfully deleted", true);
            }else{
                rr = new RegistrationResponse("libearian not found",false);
            }
        }else{
            rr = new RegistrationResponse("you are not allowed to delete a librarian",false);
        }
        return rr;
    }
}
