package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

public class TokenResponse {

    @Getter@Setter
    private String token;
    @Getter@Setter
    private String alert;
    @Setter
    private boolean status;

    public TokenResponse(String token,String alert){
        this.token = token;
        this.alert = alert;
        this.status = false;
    }

    public TokenResponse(String token, String alert, boolean status) {
        this.token = token;
        this.alert = alert;
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }
}
