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

}