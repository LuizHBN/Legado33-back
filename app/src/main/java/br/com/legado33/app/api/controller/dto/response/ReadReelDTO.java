package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.category.Category;
import br.com.legado33.app.domain.reels.Reel;

public record ReadReelDTO(
    Long id,
    String link,
    String title,
    String description,
    Category category
) {
    public ReadReelDTO(Reel reel) {
        this(reel.getId(), reel.getLink(), reel.getTitle(), reel.getDescription(), reel.getCategory());
    }

}