package com.example.course_work_sd2_task2;

public class FoodQueue {
    private int queueLength;
    private Customer[] cashier;

    public FoodQueue(int queueLength) {
        this.queueLength = queueLength;

        cashier = new Customer[queueLength];
    }

    public Customer[] getCashier() {
        return cashier;
    }
    public int countCustomer() {
        int numOfCustomer = 0;
        for (Customer occupied: cashier) {
            if (occupied != null) {
                numOfCustomer++;
            }
        }
        return numOfCustomer;
    }
}
