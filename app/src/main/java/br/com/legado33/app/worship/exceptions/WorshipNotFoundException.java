package br.com.legado33.app.worship.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class WorshipNotFoundException extends NotFoundException{
    public WorshipNotFoundException(Long id) {
        super("Worship not found with id " + id);
    }
}
