package com.library.system.models;

import com.library.system.Entity.Authors;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BookDetails {
    @Getter@Setter private String id;
    @Getter@Setter private String category;
    @Getter@Setter private String title;
    @Getter@Setter private String language;
    @Getter@Setter private String publisher;
    @Setter@Getter private double price;
    @Setter@Getter private int state;
    @Setter@Getter private int year;
    @Setter@Getter private String isbn;
    @Setter@Getter List<Authors> authors;

    public BookDetails(){}

    public BookDetails(String id,String category, String title, String language, String publisher,
                       double price, int year, String isbn, List<Authors> authors, int state) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.language = language;
        this.publisher = publisher;
        this.price = price;
        this.year = year;
        this.isbn = isbn;
        this.authors = authors;
        this.state = state;
    }
}
