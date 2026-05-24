package com.miguecode.gestioncine.service;

import com.miguecode.gestioncine.domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    List<Movie> movies;
    private static final MovieService INSTANCE = new MovieService();

    public MovieService() {
        this.movies = new ArrayList<>();
    }

    public static MovieService getInstance() {
        return INSTANCE;
    }

    public void addMovie(String name, double price) {
        this.movies.add(new Movie(name, price));
    }
}
