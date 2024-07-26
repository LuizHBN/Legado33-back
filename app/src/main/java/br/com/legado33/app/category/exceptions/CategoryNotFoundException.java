package br.com.legado33.app.category.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(Long id) {
        super("Category not found with id" + id);
    }
}
