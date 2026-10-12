package com.example.socketclient;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int id;
    private static final long serialVersionUID = 3199890860738953693L;

    public User(String name,int id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
