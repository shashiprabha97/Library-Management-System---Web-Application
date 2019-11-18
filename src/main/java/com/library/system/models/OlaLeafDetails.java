package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

public class OlaLeafDetails {
    @Getter
    @Setter
    private String category;
    @Getter@Setter private String title;
    @Getter@Setter private String language;
    @Getter@Setter private String ruledKing;
    @Getter@Setter private String yearRange;
    @Getter@Setter private int year;

    public OlaLeafDetails() {
    }

    public OlaLeafDetails(String category, String title, String language, String ruledKing, String yearRange,int year) {
        this.category = category;
        this.title = title;
        this.language = language;
        this.ruledKing = ruledKing;
        this.yearRange = yearRange;
        this.year = year;
    }
}
