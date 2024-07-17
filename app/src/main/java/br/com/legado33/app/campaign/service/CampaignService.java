package br.com.legado33.app.campaign.service;

import br.com.legado33.app.campaign.Campaign;
import br.com.legado33.app.campaign.repository.CampaignRepository;
import br.com.legado33.app.campaign.dto.NewCampaignDTO;
import br.com.legado33.app.campaign.dto.ReadCampaignDTO;
import br.com.legado33.app.campaign.dto.UpdateCampaignDTO;
import br.com.legado33.app.campaign.exceptions.CampaignNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {

    private final CampaignRepository  campaignRepository;

    @Autowired
    public CampaignService(CampaignRepository repository) {
        this.campaignRepository = repository;

    }

    public ReadCampaignDTO saveNewCampaign (NewCampaignDTO campaignDTO) {
       Campaign campaign = new Campaign(campaignDTO);
        Campaign savedCampaign = campaignRepository.save(campaign);
        return new ReadCampaignDTO(savedCampaign);
    }

    public Page<ReadCampaignDTO> getAllCampaigns(Pageable page) {
        return campaignRepository.findAll(page).map(ReadCampaignDTO :: new);
    }

    public ReadCampaignDTO findById(Long id) {
        return  campaignRepository.findById(id)
                .map(ReadCampaignDTO::new)
                .orElseThrow(() -> new CampaignNotFoundException(id));
    }

    public ReadCampaignDTO update(UpdateCampaignDTO campaignDTO, Long id) {
        Campaign existingCampaign = campaignRepository
                .findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));
        updateCampaignFromDTO(campaignDTO,existingCampaign);

        return new ReadCampaignDTO(campaignRepository.save(existingCampaign));
    }


    public void delete(Long id) {
        campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(id));
        campaignRepository.deleteById(id);
    }

    private Campaign updateCampaignFromDTO(UpdateCampaignDTO campaignDTO, Campaign campaign) {
        if (!campaignDTO.description().equals(campaign.getDescription())) {
            campaign.setDescription(campaignDTO.description());
        }
        if (!campaignDTO.title().equals(campaign.getTitle())) {
            campaign.setTitle(campaignDTO.title());
        }
        return campaign;
    }
    }
