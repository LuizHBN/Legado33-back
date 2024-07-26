package br.com.legado33.app.domain.worshipMaterial.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class WorshipMaterialNotFoundException extends NotFoundException {
    public WorshipMaterialNotFoundException(Long id) {
        super("Worship not found with id " + id);
    }
}
