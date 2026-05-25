package com.miguecode.gestioncine.controller;

import com.miguecode.gestioncine.service.ComboService;
import com.miguecode.gestioncine.util.Console;
import com.miguecode.gestioncine.util.Validator;

public class ComboController {
    private final ComboService comboService = ComboService.getInstance();
    public void addCombo() {
        System.out.println("---- Añadir Combos ----");
        String comboName = Console.readValidator("Ingrese el nombre del combo: ", String::toString, Validator::isNotEmpty);
        double comboPrice = Console.readValidator("Precio del combo: ", Double::parseDouble, Validator::positiveDecimalNumber);

        this.comboService.addCombo(comboName, comboPrice);

        System.out.println("---- Combo agregado correctamente ----");

    }
}
