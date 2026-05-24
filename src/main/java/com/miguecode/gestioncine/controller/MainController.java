package com.miguecode.gestioncine.controller;

import com.miguecode.gestioncine.ui.MenuView;
import com.miguecode.gestioncine.util.Console;
import com.miguecode.gestioncine.util.Validator;

public class MainController {
    private final MovieController movieController;

    public MainController(MovieController movieController) {
        this.movieController = movieController;
    }

    public void run () {
        short op = 0;

        System.out.println("---- Bienvenido a su Aerolinea WFLYS (World Fly Solution) ----");
        do {
            MenuView.showInitialMenu();
            op = Console.readValidator("Seleccione una opción: ", Short::parseShort, Validator::positiveNumber);

            switch (op) {
                case 1:
                    this.movieController.addMovie();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("--- Ha salido del sistema ---");
                    break;
                default:
                    System.out.println("--- Opción incorrecta ---");
            }
        } while (op != 8);
    }
}
