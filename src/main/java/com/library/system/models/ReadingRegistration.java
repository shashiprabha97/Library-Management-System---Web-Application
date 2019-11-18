package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ReadingRegistration {
    @Getter@Setter private String id;
    @Getter@Setter private String category;
    @Getter@Setter private String title;
    @Getter@Setter private String language;
    @Getter@Setter private int state;//whether it is public or rare
    @Getter@Setter private String type;
    @Getter@Setter private int year;

}
