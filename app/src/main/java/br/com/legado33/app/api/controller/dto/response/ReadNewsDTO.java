package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.news.News;

public record ReadNewsDTO (
        Long id,
        String title,
        String description,
        String image
){
    public ReadNewsDTO (News news){
        this(news.getId(), news.getTitle(), news.getDescription(), news.getImage());
    }
}
