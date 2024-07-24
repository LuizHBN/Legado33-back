package br.com.legado33.app.readContent.dto;

import br.com.legado33.app.readContent.ReadContent;
import br.com.legado33.app.worshipMaterial.WorshipMaterial;

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
