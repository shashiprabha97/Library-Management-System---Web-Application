package com.library.system.models;

import lombok.Getter;
import lombok.Setter;

public class LibrarianData {
    @Setter
    @Getter
    private String username;
    @Setter@Getter
    private String firstName;
    @Setter@Getter private String lastName;

    public LibrarianData(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
