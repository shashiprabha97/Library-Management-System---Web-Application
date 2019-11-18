package com.library.system.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
public class Authors {
    @Id @Setter@Getter
    private String readingsId;
    @Setter@Getter
    private String name;

    public Authors(){}

    public Authors(String readingsId, String name) {
        this.readingsId = readingsId;
        this.name = name;
    }
}
