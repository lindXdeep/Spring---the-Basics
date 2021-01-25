package com.lindx.example.beans;

public class Client {
    private String id;
    private String fullname;

    private String greeting;

    public Client(String id, String fullname) {
        super();
        this.id = id;
        this.fullname = fullname;
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

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }
}