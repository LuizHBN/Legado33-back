package br.com.legado33.app.domain.campaign.repository;

import br.com.legado33.app.domain.campaign.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {
}
