package br.com.legado33.app.readContent.dto;

public record ReadContentDTO(
    Long id,
    String book,
    Integer initialChapter,
    Integer finalChapter,
    Integer initialVerse,
    Integer finalVerse,
    String comment,
    Long worshipMaterialId
) {
}