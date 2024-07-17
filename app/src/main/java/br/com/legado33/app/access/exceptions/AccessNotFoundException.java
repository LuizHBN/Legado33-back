package br.com.legado33.app.access.exceptions;

public class AccessNotFoundException extends RuntimeException {
        public AccessNotFoundException(Long id) {
            super("Access not found with id " + id);
        }
}
