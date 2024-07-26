package br.com.legado33.app.api.controller.dto.request.newDTO;

import br.com.legado33.app.domain.worshipMaterial.WorshipMaterial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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