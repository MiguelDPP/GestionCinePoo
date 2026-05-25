package com.miguecode.gestioncine.service;

import com.miguecode.gestioncine.domain.Combo;
import com.miguecode.gestioncine.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ComboService {
    private List<Combo> combos;
    private static final ComboService INSTANCE = new ComboService();
    private ComboService () {
        this.combos = new ArrayList<>();
    }
    public static ComboService getInstance() {
        return INSTANCE;
    }

    public void addCombo(String comboName, double comboPrice) {
        Combo combo = new Combo(comboName, comboPrice);
        this.combos.add(combo);
    }

    public List<Combo> getCombos() {
        return combos;
    }

    public Combo getComboById(long id) {
        for (Combo combo : this.combos) {
            if (combo.checkId(id)) {
                return combo;
            }
        }

        throw new EntityNotFoundException("Combo con el id "+id+" no encontrado");
    }
}
