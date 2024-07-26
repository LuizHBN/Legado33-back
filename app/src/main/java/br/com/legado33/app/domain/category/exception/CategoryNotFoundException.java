package br.com.legado33.app.domain.category.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(Long id) {
        super("Category not found with id" + id);
    }
}
