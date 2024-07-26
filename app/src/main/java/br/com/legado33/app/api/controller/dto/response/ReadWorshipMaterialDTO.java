package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.worship.Worship;
import br.com.legado33.app.domain.worshipMaterial.WorshipMaterial;

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
