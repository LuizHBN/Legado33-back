package br.com.legado33.app.campaign.exceptions;

public class CampaignNotFoundException extends RuntimeException {
    public CampaignNotFoundException(Long id) {
        super("Campaign not found with id " + id);
    }
}
