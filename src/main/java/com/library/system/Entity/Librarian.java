package com.library.system.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "librarian")
public class Librarian {

    @Id
    @Setter@Getter
    private String username;
    @Setter@Getter private String firstName;
    @Setter@Getter private String lastName;
    @Setter@Getter private String password;
    @Setter@Getter private String state;//whether principal or not
}
