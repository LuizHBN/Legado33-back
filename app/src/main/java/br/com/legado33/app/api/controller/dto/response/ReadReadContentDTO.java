package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.readContent.ReadContent;
import br.com.legado33.app.domain.worshipMaterial.WorshipMaterial;

public record ReadReadContentDTO(
        Long id,
        WorshipMaterial worshipMaterial,
        String book,
        Integer initialChapter,
        Integer finalChapter,
        Integer initialVerse,
        Integer finalVerse,
        String comment


) {
    public ReadReadContentDTO(ReadContent readContent){
        this(readContent.getId(),
                readContent.getWorshipMaterial(),
                readContent.getBook(),
                readContent.getInitialChapter(),
                readContent.getFinalChapter(),
                readContent.getInitialVerse(),
                readContent.getFinalVerse(),
                readContent.getComment());
    }
}
