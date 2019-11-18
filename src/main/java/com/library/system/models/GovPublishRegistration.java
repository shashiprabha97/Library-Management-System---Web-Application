package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class GovPublishRegistration extends GovPublishDetails {
    @Getter
    @Setter
    private String id;
    @Getter@Setter private String type;
    @Getter@Setter private int state;//whether it is public or rare

    public GovPublishRegistration(){}

    public GovPublishRegistration(String category, String title, String language, int year, String id, String type, int state) {
        super(category, title, language,year);
        this.id = id;
        this.type = type;
        this.state = state;
    }
}
