package br.com.legado33.app.news.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class NewsNotFoundException extends NotFoundException {
    public NewsNotFoundException(Long id) {
        super("News not found with id " + id);
    }
}
