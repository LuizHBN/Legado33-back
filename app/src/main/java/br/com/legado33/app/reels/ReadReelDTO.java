package br.com.legado33.app.reels;

import br.com.legado33.app.category.Category;

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