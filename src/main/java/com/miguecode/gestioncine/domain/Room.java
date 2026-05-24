package com.miguecode.gestioncine.domain;

import com.miguecode.gestioncine.util.StringOperations;

import java.util.List;

public class Room {
    private static long lastId = 0;
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

}
