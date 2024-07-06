package com.project;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private int customerId;
    private int mealId;
    private int quantity;
    private int orderId;

    public Customer(String name, int customerId, int mealId, int orderId,int quantity) {
        this.name = name;
        this.customerId = customerId;
        this.mealId = mealId;
        this.orderId = orderId;
        this.quantity =quantity;
    }

    public String getName() {
        return name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getMealId() {
        return mealId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderId() {
        return orderId;
    }
}
