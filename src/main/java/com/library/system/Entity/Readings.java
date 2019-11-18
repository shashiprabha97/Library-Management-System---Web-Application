package com.library.system.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "readings")
public class Readings {
    @Id
    @Getter@Setter
    private String id;
    @Getter@Setter private String category;
    @Getter@Setter private String title;
    @Getter@Setter private String language;
    @Getter@Setter private int state;//whether it is public or rare
    @Getter@Setter private String type;
    @Getter@Setter private String addedPersonId;
    @Getter@Setter private int year;

    public Readings(){}

    public Readings(String id, String category, String title, String language, int state, String type, String addedPersonId, int year) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.language = language;
        this.state = state;
        this.type = type;
        this.addedPersonId = addedPersonId;
        this.year = year;
    }
}
