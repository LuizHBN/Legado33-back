package br.com.legado33.app.api.controller.dto.request.updateDTO;

import br.com.legado33.app.domain.category.Category;

public record UpdateReelDTO(
        String title,
        String description,
        Category category
) {}