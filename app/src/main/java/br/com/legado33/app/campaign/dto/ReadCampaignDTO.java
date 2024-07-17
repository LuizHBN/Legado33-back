package br.com.legado33.app.campaign.dto;

import br.com.legado33.app.campaign.Campaign;

public record ReadCampaignDTO(
        Long id,
        String Title,
        String Description
) {
    public ReadCampaignDTO(Campaign savedCampaign) {
        this(savedCampaign.getId(), savedCampaign.getTitle(), savedCampaign.getDescription());
    }
}