package com.trying.developing.firebasetesting;

/**
 * Created by developing on 4/1/2018.
 */

public class Users {
    private String position;
    private String email;


    public Users() {
    }

    public Users( String position,String email) {
        this.position = position;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
