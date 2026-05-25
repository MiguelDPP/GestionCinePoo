package com.miguecode.gestioncine.service;

import com.miguecode.gestioncine.domain.Chair;
import com.miguecode.gestioncine.domain.Client;
import com.miguecode.gestioncine.domain.Movie;
import com.miguecode.gestioncine.domain.Room;
import com.miguecode.gestioncine.exception.DuplicateEntityException;
import com.miguecode.gestioncine.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private List<Room> rooms;
    private static final RoomService INSTANCE = new RoomService();

    public RoomService() {
        this.rooms = new ArrayList<>();
    }

    public static RoomService getInstance() {
        return INSTANCE;
    }

    public void addRoom(Movie movie, int rows, int cols) {
        this.rooms.add(new Room(movie, rows, cols));
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room getRoomById(long roomId) {
        for (Room room: rooms) {
            if (room.checkId(roomId)) {
                return room;
            }
        }

        throw new EntityNotFoundException("Sala con el id "+roomId+" no encontrada");
    }

    public Chair[][] getChairsByRoomId(long roomId) {
        Room room = this.getRoomById(roomId);
        return room.getChairs();
    }

    public void addChair(int chairNumber, long roomId, Client client) {
        Room room = this.getRoomById(roomId);
        Chair seat = this.getChair(chairNumber, room);
        seat.setIsAvailable(false);
        seat.setClient(client);
    }

    public Chair getChair(int chairNumber, Room room) {
        Chair chair = room.getChair(chairNumber);
        if (chair == null) {
            throw new EntityNotFoundException("La silla #"+chairNumber+" no existe");
        }
        if (!chair.getIsAvailable()) {
            throw new DuplicateEntityException("La silla #"+chairNumber+" se encuentra ocupada");
        }

        return chair;
    }

    public void checkClient(Client client, long roomId) {
        Room room = this.getRoomById(roomId);
        if (room == null) {
            throw new EntityNotFoundException("La sala #"+roomId+" no existe");
        }
        if (room.checkClient( client)) {
            throw new DuplicateEntityException("Cliente con el documento "+client.getDocumentoId()+" ya tiene apartada una silla en esta sala");
        }
    }

    public List<Room> getRoomsByClient (Client client) {
        List<Room> clientRooms = new ArrayList<>();
        for (Room room: this.rooms) {
            if (room.checkClient(client)) {
                clientRooms.add(room);
            }
        }

        return  clientRooms;
    }

    public Chair getChairByRoomAndClient(int roomId, Client client) {
        for (Room room: rooms) {
            if (room.checkId(roomId) && room.checkClient(client)) {
                return room.getChairByClient(client);
            }
        }

        throw new EntityNotFoundException("No se encuentran registros del vuelo o el documento del cliente ingresado");
    }
}
