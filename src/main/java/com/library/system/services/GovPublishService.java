package com.library.system.services;

import com.library.system.models.GovPublishDetails;
import com.library.system.models.GovPublishRegistration;
import com.library.system.models.RegistrationResponse;
import com.library.system.models.SearchReadings;

import java.util.List;

public interface GovPublishService {
    GovPublishDetails findById(String id , String token);
    RegistrationResponse saveGovPublish(GovPublishRegistration govReg, String token);
    RegistrationResponse updateGovPublish(GovPublishRegistration govReg,String token,String id);
    RegistrationResponse deleteGovPublish(String token,String id);
    List<SearchReadings> searchGovPublish(String type, String searchVal, String token);
}
