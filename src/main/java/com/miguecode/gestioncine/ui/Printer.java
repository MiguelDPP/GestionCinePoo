package com.miguecode.gestioncine.ui;

import com.miguecode.gestioncine.domain.Chair;
import com.miguecode.gestioncine.domain.Combo;
import com.miguecode.gestioncine.domain.Movie;
import com.miguecode.gestioncine.domain.Room;

import java.util.List;

public class Printer {
    public static void printMovies(List<Movie> movies) {
        System.out.printf("%-24sListado De Peliculas\n","");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-50s %-10s\n", "Id", "Nombre", "Precio");
        System.out.println("----------------------------------------------------------------------------------------");
        if (movies.isEmpty()) {
            System.out.println("No hay vuelos disponibles");
            return;
        }
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        System.out.println();
    }


    public static void printRooms(List<Room> rooms) {
        System.out.printf("%-24sListado De Salas\n","");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-50s %-10s %-10s\n", "Id", "Nombre de Película", "Precio", "Total de sillas");
        System.out.println("----------------------------------------------------------------------------------------");
        if (rooms.isEmpty()) {
            System.out.println("No hay vuelos disponibles");
            return;
        }
        for (Room room : rooms) {
            System.out.println(room);
        }
        System.out.println();
    }

    public static void printChairs(Chair[][] chairs) {
        System.out.printf("%-24sListado De Sillas\n","");
        System.out.println("Las sillas marcadas con XX se encuentran ocupadas");

        int seatQuantity = chairs.length*chairs[0].length;
        int length = String.valueOf(seatQuantity).length();
        System.out.print("\t");
        for(int j=0; j<chairs[0].length; j++) {
            System.out.printf("%-"+(length+4)+"s", "C"+(j+1));
        }
        for(int i=0; i<chairs.length; i++) {
            System.out.printf("\nF%d\t", (i+1));
            for(int j=0; j<chairs[i].length; j++) {
                System.out.printf("%-"+(length+4)+"s", "["+(!chairs[i][j].getIsAvailable()?"x".repeat(length):chairs[i][j].getChairNumber())+"]");
            }
        }

        System.out.println();
    }

    public static void printCombos(List<Combo> combos) {
        System.out.printf("%-24sListado De Combos\n","");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-10s\n", "Id", "Nombre", "Precio");
        System.out.println("----------------------------------------------------------------------------------------");
        if (combos.isEmpty()) {
            System.out.println("No hay Combos disponibles");
            return;
        }
        for (Combo combo : combos) {
            System.out.println(combo);
        }
        System.out.println();
    }

    public static void printResumeCombos(List<Combo> combos, double total) {
        printCombos(combos);
        if (!combos.isEmpty()) {
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.printf("%-30s %-10s\n", "Total en comida", total);
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }


//    ------------------------------------------------------------------
//    public static void printPlanes(List<Plane> planes) {
//        System.out.printf("%-24sListado De Aviones\n","");
//        System.out.println("----------------------------------------------------------------------------------------");
//        System.out.printf("%-10s %-20s %-20s %-10s %-10s\n", "Id", "Destino", "Piloto", "Precio","Asientos Disponibles");
//        System.out.println("----------------------------------------------------------------------------------------");
//        if (planes.isEmpty()) {
//            System.out.println("No hay vuelos disponibles");
//            return;
//        }
//        for (Plane plane : planes) {
//            System.out.println(plane);
//        }
//        System.out.println();
//    }
//
//    public static void printFoods(List<Food> foods) {
//        System.out.printf("%-24sListado De Comidas\n","");
//        System.out.println("----------------------------------------------------------------------------------------");
//        System.out.printf("%-10s %-20s %-10s\n", "Id", "Nombre", "Precio");
//        System.out.println("----------------------------------------------------------------------------------------");
//        if (foods.isEmpty()) {
//            System.out.println("No hay comida en el menú");
//            return;
//        }
//        for (Food food : foods) {
//            System.out.println(food);
//        }
//        System.out.println();
//    }
//
//    public static void printResumeFoods(List<Food> foods, double total) {
//        printFoods(foods);
//        if (!foods.isEmpty()) {
//            System.out.println("----------------------------------------------------------------------------------------");
//            System.out.printf("%-30s %-10s\n", "Total en comida", total);
//            System.out.println("----------------------------------------------------------------------------------------");
//        }
//    }
//
//    public static void printSeats(Seat[][] seats) {
//        System.out.printf("%-24sListado De Asientos\n","");
//        System.out.println("Asientos marcados con XX se encuentran ocupados");
//
//        int seatQuantity = seats.length*seats[0].length;
//        int length = String.valueOf(seatQuantity).length();
//        System.out.print("\t");
//        for(int j=0; j<seats[0].length; j++) {
//            System.out.printf("%-"+(length+4)+"s", "C"+(j+1));
//        }
//        for(int i=0; i<seats.length; i++) {
//            System.out.printf("\nF%d\t", (i+1));
//            for(int j=0; j<seats[i].length; j++) {
//                System.out.printf("%-"+(length+4)+"s", "["+(!seats[i][j].getIsAvailable()?"x".repeat(length):seats[i][j].getSeatNumber())+"]");
//            }
//        }
//
//        System.out.println();
//    }
}
