package com.library.system.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Setter@Getter
    private String readingsId;
    @Setter@Getter
    private String publisher;
    @Setter@Getter
    private double price;
    @Setter@Getter
    private int year;
    @Setter@Getter
    private String isbn;

    public Book(){}

    public Book(String readingsId, String publisher, double price, int year, String isbn) {
        this.readingsId = readingsId;
        this.publisher = publisher;
        this.price = price;
        this.year = year;
        this.isbn = isbn;
    }
}
