package br.com.legado33.app.reels.service;

import br.com.legado33.app.category.Category;
import br.com.legado33.app.category.dto.CategoryService.ReadCategoryDTO;
import br.com.legado33.app.category.service.CategoryService.CategoryService;
import br.com.legado33.app.reels.Reel;
import br.com.legado33.app.reels.dto.NewReelDTO;
import br.com.legado33.app.reels.dto.ReadReelDTO;
import br.com.legado33.app.reels.dto.UpdateReelDTO;
import br.com.legado33.app.reels.exceptions.ReelNotFoundException;
import br.com.legado33.app.reels.repository.ReelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReelService {

    private final ReelRepository reelRepository;
    private final CategoryService categoryService;

    @Autowired
    public ReelService(ReelRepository reelRepository, CategoryService categoryService) {
        this.reelRepository = reelRepository;
        this.categoryService = categoryService;
    }

    public ReadReelDTO saveNewReel(NewReelDTO reelDTO) {
        ReadCategoryDTO categoryDTO = categoryService.findById(reelDTO.category());
        Category category = new Category(categoryDTO);
        Reel reel = new Reel(reelDTO, category);
        Reel savedReel = reelRepository.save(reel);
        return new ReadReelDTO(savedReel);
    }

    public Page<ReadReelDTO> getAllReels(Pageable page) {
        return reelRepository.findAll(page).map(ReadReelDTO :: new);
    }

    public ReadReelDTO findById(Long id) {
        return  reelRepository.findById(id)
                .map(ReadReelDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Reel not found with id: " + id));
    }

    public ReadReelDTO update(UpdateReelDTO reelDTO, Long id) {
        Reel existingReel = reelRepository
                .findById(id)
                .orElseThrow(() -> new ReelNotFoundException(id));
        existingReel = updateNotNullFieldFromDTO(reelDTO,existingReel);

        return new ReadReelDTO(reelRepository.save(existingReel));
    }

    public void delete(Long id) {
        Reel existingReel = reelRepository
                .findById(id)
                .orElseThrow(() -> new ReelNotFoundException(id));

        reelRepository.deleteById(id);
    }

    private Reel updateNotNullFieldFromDTO(UpdateReelDTO reelDTO, Reel reel ){
        if (reelDTO.title() != null) {
            reel.setTitle(reelDTO.title());
        }
        if (reelDTO.description() != null) {
           reel.setDescription(reelDTO.description());
        }
        if (reelDTO.category() != null) {
            ReadCategoryDTO categoryDTO = categoryService.findById(reelDTO.category());
            Category category = new Category(categoryDTO);
            reel.setCategory(category);
        }
        return reel;
    }
}