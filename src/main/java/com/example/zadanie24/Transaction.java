package com.example.zadanie24;

import java.sql.Date;

public class Transaction {

    private Integer id;
    private Type type;
    private String description;
    private double amount;
    private Date date;

    public Transaction(Integer id, Type type, String description, double amount, Date data) {
        this(type, description, amount, data);
        this.id = id;
    }

    public Transaction(Type type, String description, double amount, Date date) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + description + ", kwota: " + amount + "zł, data:" + date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
