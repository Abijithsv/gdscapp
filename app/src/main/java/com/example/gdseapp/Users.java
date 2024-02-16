package com.example.gdseapp;

public class Users {

    String Name,Age,Strength;

    public Users() {

    }

    public Users(String name, String age, String strength) {
        Name = name;
        Age = age;
        Strength = strength;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getStrength() {
        return Strength;
    }

    public void setStrength(String strength) {
        Strength = strength;
    }
}
