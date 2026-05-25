package com.miguecode.gestioncine.domain;

import com.miguecode.gestioncine.util.StringOperations;

import java.util.List;

public class Room {
    private static long lastId = 1;
    private final long id;
    private final Movie movie;
    private final Chair[][] chairs;

    public Room(Movie movie, int rows, int cols) {
        this.id = lastId++;
        this.movie = movie;
        this.chairs = new Chair[rows][cols];
        this.fillChairs();
    }

    private void fillChairs() {
        int counter = 1;
        for (int i = 0; i < chairs.length; i++) {
            for (int j = 0; j < chairs[i].length; j++) {
                chairs[i][j] = new Chair(StringOperations.getNumberFormatted(counter, this.getChairQuantity()));
                counter++;
            }
        }
    }

    public int getChairQuantity() {
        return chairs.length * chairs[0].length;
    }

    public boolean checkId(long id) {
        return this.id == id;
    }

    public boolean checkClient (Client client) {
        for (Chair[] rowChair: this.chairs) {
            for (Chair chair: rowChair) {
                if (chair.checkClient(client)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Chair[][] getChairs() {
        return chairs;
    }

    public Chair getChair(int chairNumber) {
        int columns = this.chairs[0].length;
        int row = (chairNumber/columns);
        int column = (chairNumber%columns) - 1;
        if (column < 0) {
            column = columns - 1;
            row--;
        }
        return chairs[row][column];
    }

    public Chair getChairByClient (Client client) {
        for (Chair[] rowChair: this.chairs) {
            for (Chair chair: rowChair) {
                if (chair.checkClient(client)) {
                    return chair;
                }
            }
        }
        return null;
    }

    public String getMovieName() {
        return this.movie.getName();
    }

    public double getMoviePrice() {
        return this.movie.getPrice();
    }

    @Override
    public String toString() {
        return String.format("%-10s %-50s %-10s %-10s", this.id, this.movie.getName(), this.movie.getPrice(), this.getChairQuantity());
    }



}
