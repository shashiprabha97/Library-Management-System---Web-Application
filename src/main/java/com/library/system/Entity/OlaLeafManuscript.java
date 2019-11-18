package com.library.system.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ola_leaf")
public class OlaLeafManuscript {

    @Id
    @Getter@Setter
    private String readingsId;
    @Getter@Setter
    private String ruledKing;
    @Getter@Setter
    private String yearRange;

    public OlaLeafManuscript() {
    }

    public OlaLeafManuscript(String readingsId, String ruledKing, String yearRange) {
        this.readingsId = readingsId;
        this.ruledKing = ruledKing;
        this.yearRange = yearRange;
    }
}
