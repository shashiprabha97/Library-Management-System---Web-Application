package com.library.system.data;

import com.library.system.Entity.Librarian;
import com.library.system.Entity.User;

public class AccessData {
    public final static String FOREIGN = "FOREIGN";
    public final static String SRILANKA = "SL";

    public final static String BOOK = "BOOK";
    public final static String MAGAZINE = "MAGAZINE";
    public final static String NEWS_PAPER = "NEWS_PAPER";
    public final static String GOV_PUBLICATION = "GOV_PUBLICATION";
    public final static String OLA_LEAF = "OLA_LEAF";

    public final static int PUBLIC = 0;
    public final static int RARE = 1;

    public final static String PRINCIPAL = "PRINCIPAL";
    public final static String NORMAL = "NORMAL";

    public final static int USERTYPE = 0;
    public final static int USERNAME = 1;

    public final static String CATEGORY = "category";
    public final static String TITLE = "title";
    public final static String YEAR = "year";
    public final static String LANGUAGE = "language";

    public static String tokenGenerator(User user){
        String userId = (user.getUserType().equals(AccessData.FOREIGN))?user.getPassportId():user.getIdNumber();
        return user.getUserType()+"."+user.getUsername()+"."+userId;
    }
    public static String tokenGenerator(Librarian user){
        return user.getState()+"."+user.getUsername();
    }
}
