package com.miguecode.gestioncine.controller;

import com.miguecode.gestioncine.service.MovieService;
import com.miguecode.gestioncine.util.Console;
import com.miguecode.gestioncine.util.Validator;

public class MovieController {
    private final MovieService movieService =  MovieService.getInstance();

    public void addMovie() {
        System.out.println("---- Agregar Pelicula ----");
        String movieName = Console.readValidator("Ingrese el nombre de la pelicula: ", String::toString, Validator::isNotEmpty);
        double moviePrice = Console.readValidator("Ingrese el valor de la pelicula: ", Double::parseDouble, Validator::positiveNumber);

        this.movieService.addMovie(movieName, moviePrice);

        System.out.println("---- Pelicula agregada exitosamente ----");
    }
}
