package com.library.system.services;

import com.library.system.models.MagazineDetails;
import com.library.system.models.MagazineRegistration;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.SearchReadings;

import java.util.List;

public interface MagazineService {
    MagazineRegistration findById(String id , String token);
    RegistrationResponse saveMagazine(MagazineRegistration mgReg, String token);
    RegistrationResponse updateMagazine(MagazineRegistration magReg,String token,String id);
    RegistrationResponse deleteMagazine(String token,String id);
    List<SearchReadings> searchMagazine(String type, String searchVal, String token);
}
