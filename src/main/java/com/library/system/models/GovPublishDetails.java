package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

public class GovPublishDetails {

    @Getter@Setter private String category;
    @Getter@Setter private String title;
    @Getter@Setter private String language;
    @Setter@Getter private int year;

    public GovPublishDetails(){}

    public GovPublishDetails(String category, String title, String language, int year) {
        this.category = category;
        this.title = title;
        this.language = language;
        this.year = year;
    }
}
