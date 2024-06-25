package br.com.legado33.app.reels.exceptions;

public class ReelNotFoundException extends RuntimeException {
    public ReelNotFoundException(Long id) {
        super("Reel not found with id " + id);
    }
}