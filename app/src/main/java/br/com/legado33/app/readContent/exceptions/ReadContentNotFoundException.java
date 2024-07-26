package br.com.legado33.app.readContent.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class ReadContentNotFoundException extends NotFoundException {
    public ReadContentNotFoundException(Long id) {
        super("Read content not found with id " + id);
    }
}
