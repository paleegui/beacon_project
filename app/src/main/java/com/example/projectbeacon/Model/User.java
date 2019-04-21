package com.example.projectbeacon.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private String email;
    private String tel;
    private Date bday;
    private String pattern = "yyyy/MM/dd";


    public User(){}

    public User(String username, String password, String email, String tel, Date bday) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        SimpleDateFormat inputFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = inputFormat.parse(bday.toString());
            this.bday = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = inputFormat.parse(bday.toString());
            this.bday = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public long getTimeMillis(){
        long millis = this.bday.getTime();
        return millis;
    }
}
