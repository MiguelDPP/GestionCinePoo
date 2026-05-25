package com.miguecode.gestioncine.domain;

import java.util.ArrayList;
import java.util.List;

public class Chair {
    private static int lastId = 1;
    private int id;
    private boolean isAvailable = true;
    private final String chairNumber;
    private Client client;
    List<Combo> combos;

    public Chair(String chairNumber) {
        this.id = lastId++;
        this.combos = new ArrayList<>();
        this.chairNumber = chairNumber;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public String getChairNumber() {
        return chairNumber;
    }

    public List<Combo> getCombos() {
        return combos;
    }

    public double getComboTotalPrice () {
        double total = 0;
        for(Combo combo: this.combos) {
            total+= combo.getPrice();
        }

        return total;
    }

    //Ocupar asiento
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addCombo(Combo combo) {
        this.combos.add(combo);
    }

    public boolean checkClient(Client client) {
        if (this.client == null) return false;
        return this.client.getDocumentoId() ==  client.getDocumentoId(); // Igual se puede comparar referencias
    }
}
