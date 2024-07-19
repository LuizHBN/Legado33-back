package br.com.legado33.app.news.dto;

import br.com.legado33.app.news.News;

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
