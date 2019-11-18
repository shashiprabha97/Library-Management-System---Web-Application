package com.library.system.services;

import com.library.system.models.NewspaperDetails;
import com.library.system.models.NewspaperRegistration;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.SearchReadings;

import java.util.List;

public interface NewspaperService {
    NewspaperDetails findById(String id , String token);
    RegistrationResponse saveNewspaper(NewspaperRegistration newReg, String token);
    RegistrationResponse updateMagazine(NewspaperRegistration newReg, String token, String id);
    RegistrationResponse deleteMagazine(String token,String id);
    List<SearchReadings> searchNewsPaper(String type, String searchVal, String token);
}

