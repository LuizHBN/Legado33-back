package br.com.legado33.app.news.exceptions;

public class NewsNotFoundException extends RuntimeException {
    public NewsNotFoundException(Long id) {
        super("News not found with id " + id);
    }
}
