package br.com.legado33.app.readContent.dto;

import br.com.legado33.app.worship.Worship;
import br.com.legado33.app.worshipMaterial.WorshipMaterial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewReadContentDTO(
    @NotNull
    @NotBlank
  
    String book,

    @NotNull
    Integer initialChapter,

    @NotNull
    Integer finalChapter,

    @NotNull
    Integer initialVerse,

    @NotNull
    Integer finalVerse,

    @NotNull
    @NotBlank
    String comment,

    @NotNull
    WorshipMaterial worshipMaterial
) {
}