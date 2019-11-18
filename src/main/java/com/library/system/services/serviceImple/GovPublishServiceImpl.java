package com.library.system.services.serviceImple;

import com.library.system.Entity.GovPublish;
import com.library.system.Entity.Magazine;
import com.library.system.Entity.Readings;
import com.library.system.data.AccessData;
import com.library.system.models.*;
import com.library.system.repositories.GovPublishRepository;
import com.library.system.repositories.ReadingsRepository;
import com.library.system.services.GovPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GovPublishServiceImpl implements GovPublishService {
    @Autowired
    ReadingsRepository readingsRepository;
    @Autowired
    GovPublishRepository govPublishRepository;

    //access controll by user
    public GovPublishRegistration findById(String id , String token) {
        String userType[] = token != null ? token.split("\\.") : null;
        GovPublishRegistration govPublishDetails = null;
        Optional<Readings> optRead = this.readingsRepository.findById(id);
        System.out.println(token);
        if (optRead.isPresent()) {
            Readings readings = optRead.get();

            if (readings.getType().equals(AccessData.GOV_PUBLICATION)&&(userType[AccessData.USERTYPE].equals(AccessData.FOREIGN)||
                    userType[AccessData.USERTYPE].equals(AccessData.SRILANKA)||userType[AccessData.USERTYPE].equals(AccessData.NORMAL)||userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL))) {

                if (!(readings.getState() == AccessData.RARE && userType[AccessData.USERTYPE].equals(AccessData.FOREIGN))) {
                    GovPublish govPublish = this.govPublishRepository.findById(id).get();

                    govPublishDetails = new GovPublishRegistration(readings.getCategory(),readings.getTitle(),readings.getLanguage(),readings.getYear(),
                            readings.getId(),readings.getType(),readings.getState());
                }
            } else {
                //not magazine
            }
        }

        return govPublishDetails;
    }

    public RegistrationResponse saveGovPublish(GovPublishRegistration govReg, String token){
        String []tokenData = (token!=null)?token.split("\\."):null;
        RegistrationResponse regResponse = null;
        try {
            Optional<Readings> seachOlaleaf = this.readingsRepository.findById(govReg.getId());
            if(!seachOlaleaf.isPresent()){
                Readings readings = new Readings(govReg.getId(),govReg.getCategory(),govReg.getTitle(), govReg.getLanguage(),govReg.getState(), AccessData.GOV_PUBLICATION, tokenData[AccessData.USERNAME],govReg.getYear());
                GovPublish govPublish = new GovPublish(govReg.getId(),govReg.getYear());

                if (tokenData[AccessData.USERTYPE].equals(AccessData.PRINCIPAL) || tokenData[AccessData.USERTYPE].equals(AccessData.NORMAL)) {

                    this.readingsRepository.save(readings);
                    this.govPublishRepository.save(govPublish);

                    regResponse = new RegistrationResponse("new publication is added successfully", true);

                } else {
                    regResponse = new RegistrationResponse("you have no authorized access", false);
                }
            }else {
                regResponse = new RegistrationResponse("Government publish id already exist", false);
            }

        }catch (NullPointerException ex ){
            regResponse = new RegistrationResponse("access token is not valid", false);
        }catch (ArrayIndexOutOfBoundsException ex){
            regResponse = new RegistrationResponse("access token is not valid", false);
        }
        return regResponse;
    }

    public RegistrationResponse updateGovPublish(GovPublishRegistration govReg,String token,String id){
        String []tokenSplit = (token!=null)?token.split("\\."):null;
        RegistrationResponse registrationResponse = null;
        Optional<Readings> optRead = this.readingsRepository.findById(id);
        try {
            if(tokenSplit[AccessData.USERTYPE].equals(AccessData.NORMAL) || tokenSplit[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)) {

                if (optRead.isPresent()) {
                    Readings readings = optRead.get();
                    GovPublish govPublish = this.govPublishRepository.findById(id).get();

                    readings.setTitle(govReg.getTitle());
                    readings.setLanguage(govReg.getLanguage());
                    readings.setCategory(govReg.getCategory());
                    readings.setYear(govReg.getYear());

                    if (govReg.getState() != readings.getState() && !tokenSplit[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)) {
                        return new RegistrationResponse("user has no permission to change government publication state", false);
                    } else {
                        readings.setState(govReg.getState());
                    }

                    govPublish.setYear(govReg.getYear());

                    this.readingsRepository.save(readings);
                    this.govPublishRepository.save(govPublish);
                    registrationResponse = new RegistrationResponse("Successfully updated",true);
                }else{
                    registrationResponse = new RegistrationResponse("selected government publication is not available",false);
                }
            }else {
                registrationResponse = new RegistrationResponse("not authorize access to update data",false);
            }
        }catch (NullPointerException ex){
            registrationResponse = new RegistrationResponse("User token is not valid",false);
        }
        return registrationResponse;
    }
    //
    public RegistrationResponse deleteGovPublish(String token,String id){
        String []tokenSplit = (token!=null)?token.split("\\."):null;
        RegistrationResponse registrationResponse = null;

        try{
            if(tokenSplit[AccessData.USERTYPE].equals(AccessData.NORMAL) || tokenSplit[AccessData.USERTYPE].equals(AccessData.PRINCIPAL)){
                Optional<Readings> optRead = this.readingsRepository.findById(id);
                if(optRead.isPresent()){
                    Readings readings = optRead.get();
                    GovPublish govPublish = this.govPublishRepository.findById(id).get();

                    this.govPublishRepository.delete(govPublish);
                    this.readingsRepository.delete(readings);
                    registrationResponse = new RegistrationResponse("Successfully Deleted", true);
                }else {
                    registrationResponse = new RegistrationResponse("no government publication found", false);
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
    public List<SearchReadings> searchGovPublish(String type, String searchVal, String token) {
        String userType[] = token != null ? token.split("\\.") : null;
        List<Readings> readingsList = null;
        List<SearchReadings> searchReadings = new ArrayList<>();
        try {
            switch (type) {
                case AccessData.YEAR: {
                    if (userType[AccessData.USERTYPE].equals(AccessData.SRILANKA) || userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL) || userType[AccessData.USERTYPE].equals(AccessData.NORMAL) || userType[AccessData.USERTYPE].equals(AccessData.FOREIGN)) {

                        readingsList = this.readingsRepository.findByYear(searchVal, AccessData.GOV_PUBLICATION, AccessData.PUBLIC);

                        if (userType[AccessData.USERTYPE].equals(AccessData.SRILANKA) || userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL) || userType[AccessData.USERTYPE].equals(AccessData.NORMAL)) {
                            List<Readings> optionalList = this.readingsRepository.findByYear(searchVal, AccessData.GOV_PUBLICATION, AccessData.RARE);
                            readingsList.addAll(optionalList);
                        }
                    }
                    break;
                }
                case AccessData.CATEGORY: {
                    if (userType[AccessData.USERTYPE].equals(AccessData.SRILANKA) || userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL) || userType[AccessData.USERTYPE].equals(AccessData.NORMAL) || userType[AccessData.USERTYPE].equals(AccessData.FOREIGN)) {

                        readingsList = this.readingsRepository.findByCategory(searchVal, AccessData.GOV_PUBLICATION, AccessData.PUBLIC);

                        if (userType != null && (userType[AccessData.USERTYPE].equals(AccessData.SRILANKA) || userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL) || userType[AccessData.USERTYPE].equals(AccessData.NORMAL))) {
                            List<Readings> optionalList = this.readingsRepository.findByCategory(searchVal, AccessData.GOV_PUBLICATION, AccessData.RARE);
                            readingsList.addAll(optionalList);
                        }
                    }
                    break;
                }
                case AccessData.TITLE: {
                    if (userType[AccessData.USERTYPE].equals(AccessData.SRILANKA) || userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL) || userType[AccessData.USERTYPE].equals(AccessData.NORMAL) || userType[AccessData.USERTYPE].equals(AccessData.FOREIGN)) {

                        readingsList = this.readingsRepository.findByTitle(searchVal, AccessData.GOV_PUBLICATION, AccessData.PUBLIC);

                        if (userType != null && (userType[AccessData.USERTYPE].equals(AccessData.SRILANKA) || userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL) || userType[AccessData.USERTYPE].equals(AccessData.NORMAL))) {
                            List<Readings> optionalList = this.readingsRepository.findByTitle(searchVal, AccessData.GOV_PUBLICATION, AccessData.RARE);
                            readingsList.addAll(optionalList);
                        }
                    }
                    break;
                }
                case AccessData.LANGUAGE: {
                    if (userType[AccessData.USERTYPE].equals(AccessData.SRILANKA) || userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL) || userType[AccessData.USERTYPE].equals(AccessData.NORMAL) || userType[AccessData.USERTYPE].equals(AccessData.FOREIGN)) {

                        readingsList = this.readingsRepository.findByLanguage(searchVal, AccessData.GOV_PUBLICATION, AccessData.PUBLIC);

                        if (userType != null && (userType[AccessData.USERTYPE].equals(AccessData.SRILANKA) || userType[AccessData.USERTYPE].equals(AccessData.PRINCIPAL) || userType[AccessData.USERTYPE].equals(AccessData.NORMAL))) {
                            List<Readings> optionalList = this.readingsRepository.findByLanguage(searchVal, AccessData.GOV_PUBLICATION, AccessData.RARE);
                            readingsList.addAll(optionalList);
                        }
                    }
                    break;
                }
            }
            readingsList.forEach(v -> {
                SearchReadings sr = new SearchReadings(v.getId(), v.getCategory(), v.getTitle(), v.getType());
                searchReadings.add(sr);
            });
        } catch (NullPointerException ex) {

        }
        return searchReadings;
    }
}
