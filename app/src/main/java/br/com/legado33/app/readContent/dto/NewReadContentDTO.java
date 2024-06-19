package br.com.legado33.app.readContent.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewReadContentDTO(
    @NotNull
    @NotBlank
    @Size(max = 255)
    String book,

    @NotNull
    Integer initialChapter,

    @NotNull
    Integer finalChapter,

    @NotNull
    Integer initialVerse,

    @NotNull
    Integer finalVerse,

    @Size(max = 1020)
    String comment,

    @NotNull
    Long worshipMaterialId
) {
}