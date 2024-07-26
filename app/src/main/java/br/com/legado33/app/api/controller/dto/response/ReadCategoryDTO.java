package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.category.Category;

public record ReadCategoryDTO(Long id, String title) {

    public ReadCategoryDTO(Category category){
        this(category.getId(), category.getTitle());
    }
}
