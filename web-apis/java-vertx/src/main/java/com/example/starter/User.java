package com.example.starter;

public class User {
    public int id;
    public String firstName;
    public String lastName;
    public int age;
    public String Framework;

    public User(
            int id,
            String firstName,
            String lastName,
            int age,
            String framework
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        Framework = framework;
    }
}