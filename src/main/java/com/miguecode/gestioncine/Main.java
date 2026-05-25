package com.miguecode.gestioncine;

import com.miguecode.gestioncine.controller.ComboController;
import com.miguecode.gestioncine.controller.MainController;
import com.miguecode.gestioncine.controller.MovieController;
import com.miguecode.gestioncine.controller.RoomController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        new MainController(new MovieController(), new RoomController(), new ComboController()).run();
    }
}

