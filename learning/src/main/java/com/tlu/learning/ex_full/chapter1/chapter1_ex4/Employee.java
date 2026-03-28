package com.tlu.learning.ex_full.chapter1.chapter1_ex4;

import java.util.UUID;

public class Employee {
    private String id;
    private String name;
    private String dob; // Date of birth
    private Gender gender;
    private double salary;
    private String phone;

    public Employee() {
    }

    public Employee(String name, String dob, Gender gender, double salary, String phone) {
        this.id = UUID.randomUUID().toString(); // Tự sinh ID duy nhất
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.salary = salary;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}