package br.com.legado33.app.domain.news.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class NewsNotFoundException extends NotFoundException {
    public NewsNotFoundException(Long id) {
        super("News not found with id " + id);
    }
}
