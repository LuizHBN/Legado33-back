package br.com.legado33.app.worshipMaterial.dto;

import br.com.legado33.app.worship.Worship;
import br.com.legado33.app.worshipMaterial.WorshipMaterial;

public record ReadWorshipMaterialDTO(
        Long id,
        Worship worship,
        String description,
        String media,
        String comment
) {
    public ReadWorshipMaterialDTO(WorshipMaterial worshipMaterial) {
        this(worshipMaterial.getId(),
                worshipMaterial.getWorship(),
                worshipMaterial.getDescription(),
                worshipMaterial.getMedia(),
                worshipMaterial.getComment());
    }
}
