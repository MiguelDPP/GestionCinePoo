package com.miguecode.gestioncine.controller;

import com.miguecode.gestioncine.domain.*;
import com.miguecode.gestioncine.exception.EntityNotFoundException;
import com.miguecode.gestioncine.service.ClientService;
import com.miguecode.gestioncine.service.ComboService;
import com.miguecode.gestioncine.service.MovieService;
import com.miguecode.gestioncine.service.RoomService;
import com.miguecode.gestioncine.ui.Printer;
import com.miguecode.gestioncine.util.Console;
import com.miguecode.gestioncine.util.Validator;

import java.util.List;

public class RoomController {
    private final RoomService roomService = RoomService.getInstance();
    private final MovieService movieService = MovieService.getInstance();
    private final ClientService clientService = ClientService.getInstance();
    private final ComboService comboService = ComboService.getInstance();

    public void addRoom() {
        System.out.println("---- Crear Sala ----");
        try {
            List<Movie> movies = movieService.getMovies();
            Printer.printMovies(movies);
            if (movies.isEmpty()) return;

            long movieId = Console.readValidator("Ingrese el id de la pelicula a agregar a la sala: ", Long::parseLong, Validator::positiveNumber);

            Movie movie = movieService.getMovieById(movieId);

            int rows = Console.readValidator("Ingrese la cantidad de filas en sillas de la sala: ", Integer::parseInt, Validator::positiveNumber);
            int cols = Console.readValidator("Ingrese la cantidad de columnas en sillas de la sala: ", Integer::parseInt, Validator::positiveNumber);

            this.roomService.addRoom(movie, rows, cols);

            System.out.println("---- Sala creada correctamente ----");

        } catch (RuntimeException e) {
            Console.printException(e);
        }
    }

    public long printChairs() {
        try {
            Printer.printRooms(this.roomService.getRooms());
            long roomId = Console.readValidator("Ingrese el id de sala para ver sillas disponibles: ", Long::parseLong, Validator::positiveNumber);
            Chair[][] chairs = this.roomService.getChairsByRoomId(roomId);
            Printer.printChairs(chairs);
            return roomId;
        } catch (RuntimeException e) {
            Console.printException(e);
        }
        return -1;
    }

    public void sellTickets() {
        System.out.println("---- Vender Entradas ----");
        try {
            long roomId = this.printChairs();
            int chairNumber = Console.readValidator("Ingrese el numero de silla que desea comprar: ", Integer::parseInt, Validator::positiveNumber);
            long documentId = Console.readValidator("Ingrese su numero de documento: ", Long::parseLong, Validator::positiveNumber);
            Client client = clientService.getClient(documentId);
            if (client == null) {
                String name = Console.read("Ingrese su nombre: ", String::toString);

                client = clientService.createClient(documentId, name);
            } else {
                // Check client: Se pretende realizar de modo que solo se pueda pedir una silla por cliente para facilitar mostrar el total por silla,
                // Esto no es del todo valido en la vida real.
                this.roomService.checkClient(client, roomId);
            }

            this.roomService.addChair(chairNumber, roomId, client);

            System.out.println("---- Silla ocupada Correctamente ----");

        } catch (RuntimeException e) {
            Console.printException(e);
        }
    }

    public void orderCombo () {
        Printer.printCombos(comboService.getCombos());
        int comboId = Console.readValidator("Ingrese el Id del combo que desea: ", Integer::parseInt, Validator::positiveNumber);

        try {

            Combo combo = this.comboService.getComboById(comboId);
            long documentId = Console.readValidator("Ingrese su numero de documento: ", Long::parseLong, Validator::positiveNumber);
            Client client = clientService.getClient(documentId);
            if (client == null) {
                throw new EntityNotFoundException("Cliente no encontrado");
            }
            List<Room> rooms = this.roomService.getRoomsByClient(client);
            Printer.printRooms(rooms);

            int roomId = Console.readValidator("Ingrese el Id de la sala: ", Integer::parseInt, Validator::positiveNumber);
            Chair chair = this.roomService.getChairByRoomAndClient(roomId, client);
            System.out.println("--- Silla Encontrada: "+chair.getChairNumber()+" ----");

            String confirm = Console.read("Precione la tecla [S] si desea continuar, de lo contrario precione cualquier tecla: ", String::toString).toLowerCase();

            if (confirm.equals("s")) {
                chair.addCombo(combo);
                System.out.println("---- Combo agregado correctamente ----");
            } else {
                System.out.println("---- Operación cancelada ----");
            }
        } catch (RuntimeException e) {
            Console.printException(e);
        }
    }

    public void showTotalForClient() {
        try {
            long documentId = Console.readValidator("Ingrese su numero de documento: ", Long::parseLong, Validator::positiveNumber);
            Client client = clientService.getClient(documentId);

            if (client == null) {
                throw new EntityNotFoundException("Cliente no encontrado");
            }

            List<Room> rooms = this.roomService.getRoomsByClient(client);
            Printer.printRooms(rooms);

            int roomId = Console.readValidator("Ingrese el Id de la sala: ", Integer::parseInt, Validator::positiveNumber);
            Chair chair = this.roomService.getChairByRoomAndClient(roomId, client);
            Room room = this.roomService.getRoomById(roomId);

            System.out.println("---- Resumen de su factura ----");
            System.out.println("Pelicula de la sala: "+room.getMovieName());
            System.out.println("Silla Encontrada: "+chair.getChairNumber());
            System.out.println("Valor de la Pelicula: "+ room.getMoviePrice());

            double priceCombo = chair.getComboTotalPrice();
            Printer.printResumeCombos(chair.getCombos(), priceCombo);

            System.out.println("-------------------------------------------------------");
            System.out.println("Total a pagar: "+(priceCombo+room.getMoviePrice()));
            System.out.println("-------------------------------------------------------");
        } catch (RuntimeException e) {
            Console.printException(e);
        }
    }
}
