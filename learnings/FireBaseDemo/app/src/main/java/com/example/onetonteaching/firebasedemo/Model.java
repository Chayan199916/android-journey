package com.example.onetonteaching.firebasedemo;

public class Model {

    private String first, last, city, state, country;

    public Model(){}

    public Model(String first, String last, String city, String state, String country) {
        this.first = first;
        this.last = last;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

}
