package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class MagazineRegistration extends MagazineDetails {

    @Getter@Setter private String id;
    @Getter@Setter private String type;
    @Getter@Setter private int state;//whether it is public or rare

    public MagazineRegistration(){}

    public MagazineRegistration(String category, String title, String language, double price, Date issuedDate, String id, String type, int state,int year) {
        super(category, title, language, price, issuedDate,year);
        this.id = id;
        this.type = type;
        this.state = state;
    }
}
