package com.miguecode.gestioncine.domain;

public class Combo {
    private static int lastId = 1;
    private final long id;
    private String name;
    private double price;

    public Combo(String name, double price) {
        this.id = lastId++;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public boolean checkId(long id) {
        return this.id == id;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-10s", this.id, this.name, this.price);
    }
}
