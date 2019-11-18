package com.library.system.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "magazine")
public class Magazine {
    @Id
    @Setter@Getter
    private String readingsId;
    @Getter@Setter
    private double price;
    @Getter@Setter
    private java.sql.Date issuedDate;

    public Magazine(){}

    public Magazine(String readingId, double price, java.sql.Date issuedDate) {
        this.readingsId = readingId;
        this.price = price;
        this.issuedDate = issuedDate;
    }
}
