package br.com.legado33.app.domain.access.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class AccessNotFoundException extends NotFoundException {
        public AccessNotFoundException(Long id) {
            super("Access not found with id " + id);
        }
}
