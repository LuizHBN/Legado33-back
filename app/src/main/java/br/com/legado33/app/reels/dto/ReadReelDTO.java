package br.com.legado33.app.reels.dto;

import br.com.legado33.app.category.Category;
import br.com.legado33.app.reels.Reel;

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