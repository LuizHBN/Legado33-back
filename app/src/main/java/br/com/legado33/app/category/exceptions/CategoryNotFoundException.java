package br.com.legado33.app.category.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id) {
        super("Category not found with id" + id);
    }
}
