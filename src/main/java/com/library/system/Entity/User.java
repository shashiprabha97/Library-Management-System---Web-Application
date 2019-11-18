package com.library.system.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {
    @Id
    @Getter@Setter
    private String username;

    @Getter@Setter private String password;
    @Getter@Setter private String firstName;
    @Getter@Setter private String lastName;
    @Getter@Setter private Date regDate;
    @Getter@Setter private String userType;
    @Getter@Setter private String idNumber;
    @Getter@Setter private String district;
    @Getter@Setter private String countryCode;
    @Getter@Setter private String passportId;
    @Getter@Setter private int isReg;
}
