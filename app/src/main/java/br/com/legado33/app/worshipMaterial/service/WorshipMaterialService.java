package br.com.legado33.app.worshipMaterial.service;

import br.com.legado33.app.worship.Worship;
import br.com.legado33.app.worship.dto.ReadWorshipDTO;
import br.com.legado33.app.worship.service.WorshipService;
import br.com.legado33.app.worshipMaterial.WorshipMaterial;
import br.com.legado33.app.worshipMaterial.repository.WorshipMaterialRepository;
import br.com.legado33.app.worshipMaterial.dto.NewWorshipMaterialDTO;
import br.com.legado33.app.worshipMaterial.dto.ReadWorshipMaterialDTO;
import br.com.legado33.app.worshipMaterial.dto.UpdateWorshipMaterialDTO;
import br.com.legado33.app.worshipMaterial.exceptions.WorshipMaterialNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WorshipMaterialService {

    private final WorshipMaterialRepository worshipMaterialRepository;
    private final WorshipService worshipService;

    public WorshipMaterialService(WorshipMaterialRepository worshipMaterialRepository, WorshipService worshipService) {
        this.worshipMaterialRepository = worshipMaterialRepository;
        this.worshipService = worshipService;
    }

    public ReadWorshipMaterialDTO saveNewWorshipMaterial(NewWorshipMaterialDTO worshipMaterialDTO) {
        ReadWorshipDTO worshipDTO = worshipService.findWorshipById(worshipMaterialDTO.worship().getId());
        Worship worship = new Worship(worshipDTO);
        WorshipMaterial worshipMaterial = new WorshipMaterial(worshipMaterialDTO, worship);
        WorshipMaterial savedWorshipMaterial = worshipMaterialRepository.save(worshipMaterial);
        return new ReadWorshipMaterialDTO(savedWorshipMaterial);
    }

    public Page<ReadWorshipMaterialDTO> getAllWorshipMaterials(Pageable page) {
        return worshipMaterialRepository.findAll(page).map(ReadWorshipMaterialDTO::new);
    }

    public ReadWorshipMaterialDTO findWorshipMaterialById(Long id) {
        return worshipMaterialRepository.findById(id)
                .map(ReadWorshipMaterialDTO::new)
                .orElseThrow(() -> new WorshipMaterialNotFoundException(id));
    }

    public ReadWorshipMaterialDTO update(UpdateWorshipMaterialDTO worshipMaterialDTO, Long id) {
        WorshipMaterial existingWorshipMaterial = worshipMaterialRepository
                .findById(id)
                .orElseThrow(() -> new WorshipMaterialNotFoundException(id));
        updateWorshipMaterialFromDTO(worshipMaterialDTO, existingWorshipMaterial);

        return new ReadWorshipMaterialDTO(worshipMaterialRepository.save(existingWorshipMaterial));
    }

    public void delete(Long id) {
        worshipMaterialRepository.findById(id).orElseThrow(() -> new WorshipMaterialNotFoundException(id));
        worshipMaterialRepository.deleteById(id);
    }

    private WorshipMaterial updateWorshipMaterialFromDTO(UpdateWorshipMaterialDTO worshipMaterialDTO, WorshipMaterial worshipMaterial) {
        if (!worshipMaterialDTO.description().equals(worshipMaterial.getDescription())) {
            worshipMaterial.setDescription(worshipMaterialDTO.description());
        }
        if (!worshipMaterialDTO.media().equals(worshipMaterial.getMedia())) {
            worshipMaterial.setMedia(worshipMaterialDTO.media());
        }
        if (!worshipMaterialDTO.comment().equals(worshipMaterial.getComment())) {
            worshipMaterial.setComment(worshipMaterialDTO.comment());
        }
        if (!worshipMaterialDTO.worship().equals(worshipMaterial.getWorship())) {
            ReadWorshipDTO worshipDTO = worshipService.findWorshipById(worshipMaterialDTO.worship().getId());
            Worship worship = new Worship(worshipDTO);
            worshipMaterial.setWorship(worship);
        }
        return worshipMaterial;
    }
}