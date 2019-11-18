package com.library.system.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news_paper")
public class Newspaper {
    @Id
    @Setter
    @Getter
    private String readingsId;
    @Getter@Setter
    private double price;
    @Getter@Setter
    private java.sql.Date issuedDate;
    @Setter@Getter
    private String publisher;

    public Newspaper(){}

    public Newspaper(String readingId, double price, java.sql.Date issuedDate,String publisher) {
        this.readingsId = readingId;
        this.price = price;
        this.issuedDate = issuedDate;
        this.publisher = publisher;
    }
}
