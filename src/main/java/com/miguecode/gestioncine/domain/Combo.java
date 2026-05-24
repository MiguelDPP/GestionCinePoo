package com.miguecode.gestioncine.domain;

public class Combo {
    private static int lastId = 1;
    private int id;
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
}
