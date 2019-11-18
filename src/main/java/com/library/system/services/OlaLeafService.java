package com.library.system.services;

import com.library.system.models.OlaLeafDetails;
import com.library.system.models.OlaLeafRegistration;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.SearchReadings;

import java.util.List;

public interface OlaLeafService {
    OlaLeafDetails findById(String id , String token);
    RegistrationResponse saveOlaLeaf(OlaLeafRegistration olaReg, String token);
    RegistrationResponse updateOlaLeaf(OlaLeafRegistration olaReg,String token,String id);
    RegistrationResponse deleteOlaLeaf(String token,String id);
    List<SearchReadings> searchOlaLeaf(String type, String searchVal, String token);
}
