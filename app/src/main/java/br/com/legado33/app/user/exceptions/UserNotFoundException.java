package br.com.legado33.app.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("User not found with id " + id);
    }
}
