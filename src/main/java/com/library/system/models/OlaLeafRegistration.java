package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

public class OlaLeafRegistration extends OlaLeafDetails {

    @Getter@Setter private String id;
    @Getter@Setter private String type;
    @Getter@Setter private int state;//whether it is public or rare

    public OlaLeafRegistration(String category, String title, String language, String ruledKing, String yearRange, String id, String type, int state,int year) {
        super(category, title, language, ruledKing, yearRange,year);
        this.id = id;
        this.type = type;
        this.state = state;
    }
}
