package br.com.legado33.app.worshipMaterial.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class WorshipMaterialNotFoundException extends NotFoundException {
    public WorshipMaterialNotFoundException(Long id) {
        super("Worship not found with id " + id);
    }
}
