package com.library.system.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gov_publish")
public class GovPublish {

    @Id
    @Getter@Setter
    private String readingsId;
    @Getter@Setter
    private int year;

    public GovPublish() {
    }

    public GovPublish(String readingsId, int year) {
        this.readingsId = readingsId;
        this.year = year;
    }
}
