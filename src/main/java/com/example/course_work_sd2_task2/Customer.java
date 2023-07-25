package com.example.course_work_sd2_task2;

public class Customer {
    private final String firstName;
    private final String secondName;
    private final int burgersReq;

    public Customer(String firstName, String secondName, int burgersReq) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.burgersReq = burgersReq;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public String getSecondName() {
        return this.secondName;
    }
    public int getBurgersReq() {
        return this.burgersReq;
    }
}
