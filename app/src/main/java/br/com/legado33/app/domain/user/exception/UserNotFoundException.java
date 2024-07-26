package br.com.legado33.app.domain.user.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Long id){
        super("User not found with id " + id);
    }
}
