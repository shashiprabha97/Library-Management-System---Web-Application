package com.library.system.data;

import lombok.Getter;
import lombok.Setter;

public class BookAccessControl {
    @Setter@Getter
    public String token;

    public BookAccessControl (String token){
        this.token = token;
    }

//    public String accessLevel(){
//        String
//        if
//        String userType = token.split("\\.")[0];
//    }

}
