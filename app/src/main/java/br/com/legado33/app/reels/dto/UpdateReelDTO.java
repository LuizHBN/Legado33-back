package br.com.legado33.app.reels.dto;

import br.com.legado33.app.category.Category;

public record UpdateReelDTO(
        String title,
        String description,
        Category category
) {}