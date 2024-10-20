package br.com.legado33.app.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewCampaignDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateCampaignDTO;
import br.com.legado33.app.api.controller.dto.response.ReadCampaignDTO;
import br.com.legado33.app.domain.campaign.service.CampaignService;
import jakarta.validation.Valid;

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
    public ResponseEntity<Page<ReadCampaignDTO>> getAllCampaigns(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="7") int size){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(campaignService.getAllCampaigns(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadCampaignDTO> getAccessById(@PathVariable Long id){
            return ResponseEntity.ok(campaignService.findReadCampaignDTOById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadCampaignDTO> updateCampaign(@PathVariable Long id, @Valid @RequestBody UpdateCampaignDTO campaignDTO) {
        if (campaignDTO == null){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(campaignService.update(campaignDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
            campaignService.delete(id);
            return ResponseEntity.noContent().build();
    }

}
