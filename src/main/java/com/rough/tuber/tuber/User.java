package com.rough.tuber.tuber;


/**
 * Created by saleh on 3/24/18.
 */

public class User {

    public String etType;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email,String etType) {
        this.email = email;
        this.etType = etType;
    }

}