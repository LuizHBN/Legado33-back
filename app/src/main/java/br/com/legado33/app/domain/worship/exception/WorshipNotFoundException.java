package br.com.legado33.app.domain.worship.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class WorshipNotFoundException extends NotFoundException{
    public WorshipNotFoundException(Long id) {
        super("Worship not found with id " + id);
    }
}
