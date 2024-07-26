package br.com.legado33.app.domain.readContent.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class ReadContentNotFoundException extends NotFoundException {
    public ReadContentNotFoundException(Long id) {
        super("Read content not found with id " + id);
    }
}
