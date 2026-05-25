package com.miguecode.gestioncine.domain;

public class Movie {
    private static long lastId = 1;
    private long id;
    private String name;
    private double price;

    public Movie(String name, double price) {
        this.id = lastId++;
        this.name = name;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-50s %-10s", this.id, this.name, this.price);
    }

    public boolean checkId(long id) {
        return this.id == id;
    }
}