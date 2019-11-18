package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class NewspaperRegistration extends NewspaperDetails {
    @Getter
    @Setter
    private String id;
    @Getter@Setter private String type;
    @Getter@Setter private int state;//whether it is public or rare

    public NewspaperRegistration(){}

    public NewspaperRegistration(String category, String title, String language, double price, Date issuedDate, String id, String type, int state,String publisher,int year) {
        super(category, title, language, price, issuedDate,publisher,year);
        this.id = id;
        this.type = type;
        this.state = state;
    }
}
