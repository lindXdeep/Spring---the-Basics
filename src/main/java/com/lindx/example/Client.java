package com.lindx.example;

public class Client {
    private String id;
    private String fullname;

    public Client(String id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    public Client() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}