package br.com.legado33.app.reels.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class ReelNotFoundException extends NotFoundException {
    public ReelNotFoundException(Long id) {
        super("Reel not found with id " + id);
    }
}