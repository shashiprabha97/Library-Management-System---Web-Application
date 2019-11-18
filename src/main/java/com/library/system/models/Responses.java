package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

public class Responses {
    @Getter@Setter
    private String registrationAlert;

    public Responses(String registrationAlert){
        this.registrationAlert = registrationAlert;
    }
}
