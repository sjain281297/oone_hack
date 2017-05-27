package com.example.shubham.oone_hack_app;

/**
 * Created by shubham on 28/5/17.
 */

public class AuthBody {
    String email;
    String password;

    public AuthBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
