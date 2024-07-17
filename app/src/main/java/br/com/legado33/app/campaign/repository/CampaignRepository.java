package br.com.legado33.app.campaign.repository;

import br.com.legado33.app.campaign.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {
}
