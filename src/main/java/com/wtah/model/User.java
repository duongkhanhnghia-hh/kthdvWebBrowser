package com.wtah.model;

import java.io.Serializable;

public class User implements Serializable{
    private String id;
    private String msv;
    private String name;

    public User() {
    }

    public User(String id, String msv, String name) {
        this.id = id;
        this.msv = msv;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
