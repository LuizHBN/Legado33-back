package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.campaign.Campaign;

public record ReadCampaignDTO(
        Long id,
        String title,
        String description
) {
    public ReadCampaignDTO(Campaign savedCampaign) {
        this(savedCampaign.getId(), savedCampaign.getTitle(), savedCampaign.getDescription());
    }
}
