package com.entrolabs.testapplications.fininfocomtest;

public class DummyData {
    private String title;
    private String description;
    private int age;
    private String city;

    public DummyData(String title, String description, int age, String city) {
        this.title = title;
        this.description = description;
        this.age = age;
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
