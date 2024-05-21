package br.com.legado33.app.reels;

import br.com.legado33.app.category.Category;

public record UpdateReelDTO(
    Long id,
    String title,
    String description,
    Category category
) {}