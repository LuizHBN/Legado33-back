package br.com.legado33.app.readContent.dto;

import br.com.legado33.app.worshipMaterial.WorshipMaterial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateReadContentDTO(
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
