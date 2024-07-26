package br.com.legado33.app.domain.reels.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class ReelNotFoundException extends NotFoundException {
    public ReelNotFoundException(Long id) {
        super("Reel not found with id " + id);
    }
}