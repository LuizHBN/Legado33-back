package br.com.legado33.app.reels.dto;

public record UpdateReelDTO(
        String title,
        String description,
        Long category
) {}