package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

public class RegistrationResponse extends Responses {

    @Getter@Setter private boolean status;

    public RegistrationResponse(String registrationAlert,boolean status){
        super(registrationAlert);
        this.status = status;
    }
    public boolean getStatus(){
        return status;
    }
}
