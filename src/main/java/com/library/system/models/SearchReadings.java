package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

public class SearchReadings {
    @Setter@Getter
    private String id;
    @Setter@Getter
    private String category;
    @Setter@Getter
    private String title;
    @Setter@Getter
    private String type;

    public SearchReadings() {
    }

    public SearchReadings(String id,String category, String title, String type) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.type = type;
    }
}
