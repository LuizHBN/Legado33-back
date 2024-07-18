package br.com.legado33.app.campaign.controller;

import br.com.legado33.app.campaign.dto.NewCampaignDTO;
import br.com.legado33.app.campaign.dto.ReadCampaignDTO;
import br.com.legado33.app.campaign.dto.UpdateCampaignDTO;
import br.com.legado33.app.campaign.exceptions.CampaignNotFoundException;
import br.com.legado33.app.campaign.service.CampaignService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campanha")
public class CampaignController {

    private final CampaignService campaignService;
    public CampaignController(CampaignService campaignService){
        this.campaignService = campaignService;
    }

    @PostMapping("/save")
    public ResponseEntity<ReadCampaignDTO> createCampaign(@RequestBody @Valid NewCampaignDTO campaignDTO){
        return ResponseEntity.ok(campaignService.saveNewCampaign(campaignDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadCampaignDTO>> getAllCampaigns(Pageable page){
        return ResponseEntity.ok(campaignService.getAllCampaigns(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadCampaignDTO> getAccessById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(campaignService.findById(id));
        } catch (CampaignNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadCampaignDTO> updateCampaign(@PathVariable Long id, @Valid @RequestBody UpdateCampaignDTO campaignDTO) {
        if (campaignDTO == null){
            return ResponseEntity.notFound().build();
        }
        try {
            return ResponseEntity.ok(campaignService.update(campaignDTO, id));
        } catch (CampaignNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        try{
            campaignService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(CampaignNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
