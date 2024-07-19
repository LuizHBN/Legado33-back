package br.com.legado33.app.readContent.exceptions;

public class ReadContentNotFoundException extends RuntimeException{
    public ReadContentNotFoundException(Long id) {
        super("Read content not found with id " + id);
    }
}
