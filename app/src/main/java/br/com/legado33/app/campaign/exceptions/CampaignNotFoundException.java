package br.com.legado33.app.campaign.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class CampaignNotFoundException extends NotFoundException {
    public CampaignNotFoundException(Long id) {
        super("Campaign not found with id " + id);
    }
}
