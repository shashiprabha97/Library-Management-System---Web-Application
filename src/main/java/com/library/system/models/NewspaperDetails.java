package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class NewspaperDetails {
    @Getter
    @Setter
    private String category;
    @Getter@Setter private String title;
    @Getter@Setter private String language;
    @Setter@Getter private double price;
    @Getter@Setter private java.sql.Date issuedDate;
    @Getter@Setter private String publisher;
    @Getter@Setter private int year;

    public NewspaperDetails(){}

    public NewspaperDetails(String category, String title, String language,
                           double price, Date issuedDate,String publisher,int year) {
        this.category = category;
        this.title = title;
        this.language = language;
        this.price = price;
        this.issuedDate = issuedDate;
        this.publisher = publisher;
        this.year = year;

    }
}
