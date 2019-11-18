package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ForeignUser {
    @Getter
    @Setter
    private String username;

    @Getter@Setter private String password;
    @Getter@Setter private String firstName;
    @Getter@Setter private String lastName;
    @Getter@Setter private Date regDate;
    @Getter@Setter private String countryCode;
    @Getter@Setter private String passportId;

    public ForeignUser(String username, String password, String firstName, String lastName, Date regDate, String countryCode, String passportId) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.regDate = regDate;
        this.countryCode = countryCode;
        this.passportId = passportId;
    }
}
