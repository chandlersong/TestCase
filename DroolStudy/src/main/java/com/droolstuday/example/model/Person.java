package com.droolstuday.example.model;

public class Person {

    private int age;

    private String name;

    public Person() {
    }

    public int getAge() {
        return age;
    }

    public Person(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
