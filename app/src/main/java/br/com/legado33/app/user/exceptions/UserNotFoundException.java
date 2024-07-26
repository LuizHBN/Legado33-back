package br.com.legado33.app.user.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Long id){
        super("User not found with id " + id);
    }
}
