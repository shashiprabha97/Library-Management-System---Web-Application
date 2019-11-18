package com.library.system.models;

import com.library.system.Entity.Authors;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

public class MagazineDetails {
    @Getter
    @Setter
    private String category;
    @Getter@Setter private String title;
    @Getter@Setter private String language;
    @Setter@Getter private double price;
    @Getter@Setter private java.sql.Date issuedDate;
    @Getter@Setter private int year;

    public MagazineDetails(){}

    public MagazineDetails(String category, String title, String language,
                           double price, Date issuedDate,int year) {
        this.category = category;
        this.title = title;
        this.language = language;
        this.price = price;
        this.issuedDate = issuedDate;
        this.year = year;

    }
}
