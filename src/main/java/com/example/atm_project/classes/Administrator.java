package com.example.atm_project.classes;

public class Administrator extends Person {
    private String ID;
    private String password;

    public Administrator(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public Administrator(String name, String address, String ID, String password) {
        super(name, address);
        this.ID = ID;
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }
}
