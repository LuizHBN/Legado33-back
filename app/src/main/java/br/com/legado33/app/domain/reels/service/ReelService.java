package br.com.legado33.app.domain.reels.service;

import br.com.legado33.app.domain.category.Category;
import br.com.legado33.app.domain.category.service.CategoryService;
import br.com.legado33.app.domain.reels.Reel;
import br.com.legado33.app.domain.reels.exception.ReelNotFoundException;
import br.com.legado33.app.domain.reels.repository.ReelRepository;
import br.com.legado33.app.api.controller.dto.response.ReadCategoryDTO;
import br.com.legado33.app.api.controller.dto.request.newDTO.NewReelDTO;
import br.com.legado33.app.api.controller.dto.response.ReadReelDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateReelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReelService {

    private final ReelRepository reelRepository;
    private final CategoryService categoryService;

    public ReelService(ReelRepository reelRepository, CategoryService categoryService) {
        this.reelRepository = reelRepository;
        this.categoryService = categoryService;
    }

    public ReadReelDTO saveNewReel(NewReelDTO reelDTO) {
        //TODO -> Test sending a unreacheable category
            Category category = categoryService.findCategoryById(reelDTO.category());
            Reel reel = new Reel(reelDTO, category);
            Reel savedReel = reelRepository.save(reel);
            return new ReadReelDTO(savedReel);
    }

    public Page<ReadReelDTO> getAllReels(Pageable page) {
        return reelRepository.findAll(page).map(ReadReelDTO :: new);
    }

    public ReadReelDTO findReelById(Long id) {
        return  reelRepository.findById(id)
                .map(ReadReelDTO::new)
                .orElseThrow(() -> new ReelNotFoundException(id));
    }

    public ReadReelDTO update(UpdateReelDTO reelDTO, Long id) {
        Reel existingReel = reelRepository
                .findById(id)
                .orElseThrow(() -> new ReelNotFoundException(id));
        updateReelFromDTO(reelDTO,existingReel);

        return new ReadReelDTO(reelRepository.save(existingReel));
    }

    public void delete(Long id) {
        reelRepository.findById(id).orElseThrow(() -> new ReelNotFoundException(id));
        reelRepository.deleteById(id);
    }

    private Reel updateReelFromDTO(UpdateReelDTO reelDTO, Reel reel) {
        if (!reelDTO.title().equals(reel.getTitle())) {
            reel.setTitle(reelDTO.title());
        }
        if (!reelDTO.description().equals(reel.getDescription())) {
            reel.setDescription(reelDTO.description());
        }
        if (!reelDTO.category().equals(reel.getCategory())) {
            ReadCategoryDTO categoryDTO = categoryService.findReadCategoryDTOById(reelDTO.category().getId());
            Category category = new Category(categoryDTO);
                reel.setCategory(category);}
        return reel;
    }

}