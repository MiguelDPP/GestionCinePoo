package com.miguecode.gestioncine.controller;

import com.miguecode.gestioncine.ui.MenuView;
import com.miguecode.gestioncine.util.Console;
import com.miguecode.gestioncine.util.Validator;

public class MainController {
    private final MovieController movieController;
    private final RoomController roomController;
    private final ComboController comboController;

    public MainController(MovieController movieController, RoomController roomController, ComboController comboController) {
        this.movieController = movieController;
        this.roomController = roomController;
        this.comboController = comboController;
    }

    public void run () {
        short op = 0;

        System.out.println("---- Bienvenido a su Cine WCS (World Cine Solution) ----");
        do {
            MenuView.showInitialMenu();
            op = Console.readValidator("Seleccione una opción: ", Short::parseShort, Validator::positiveNumber);

            switch (op) {
                case 1:
                    this.movieController.addMovie();
                    break;
                case 2:
                    this.roomController.addRoom();
                    break;
                case 3:
                    this.roomController.sellTickets();
                    break;
                case 4:
                    this.roomController.printChairs();
                    break;
                case 5:
                    this.comboController.addCombo();
                    break;
                case 6:
                    this.roomController.orderCombo();
                    break;
                case 7:
                    this.roomController.showTotalForClient();
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
