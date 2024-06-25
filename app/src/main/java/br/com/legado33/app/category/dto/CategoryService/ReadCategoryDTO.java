package br.com.legado33.app.category.dto.CategoryService;

import br.com.legado33.app.category.Category;

public record ReadCategoryDTO(Long id, String title) {

    public ReadCategoryDTO(Category category){
        this(category.getId(), category.getTitle());
    }
}
