package br.com.legado33.app.worship.exceptions;

public class WorshipNotFoundException extends RuntimeException {
    public WorshipNotFoundException(Long id) {
        super("Worship not found with id " + id);
    }
}
