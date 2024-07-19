package br.com.legado33.app.readContent.dto;

import br.com.legado33.app.worshipMaterial.WorshipMaterial;

public record ReadReadContentDTO(
        Long id,
        WorshipMaterial worshipMaterial,
        String book,
        Integer initialChapter,
        Integer finalChapter,
        Integer intialVerse,
        Integer finalVerse,
        String comment

) {
}
