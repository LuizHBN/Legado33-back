package br.com.legado33.app.domain.campaign.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class CampaignNotFoundException extends NotFoundException {
    public CampaignNotFoundException(Long id) {
        super("Campaign not found with id " + id);
    }
}
