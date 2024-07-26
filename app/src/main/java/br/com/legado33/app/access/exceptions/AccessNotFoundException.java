package br.com.legado33.app.access.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class AccessNotFoundException extends NotFoundException {
        public AccessNotFoundException(Long id) {
            super("Access not found with id " + id);
        }
}
