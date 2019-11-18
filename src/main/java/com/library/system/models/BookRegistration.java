package com.library.system.models;

import com.library.system.Entity.Authors;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BookRegistration extends ReadingRegistration {

    @Getter@Setter private String id;
    @Getter@Setter private String category;
    @Getter@Setter private String title;
    @Getter@Setter private String language;
    @Getter@Setter private int state;//whether it is public or rare
    @Getter@Setter private String type;
    @Setter@Getter private String publisher;
    @Setter@Getter private double price;
    @Setter@Getter private int year;
    @Setter@Getter private String isbn;
    @Setter@Getter private List<Authors> authors;
}
