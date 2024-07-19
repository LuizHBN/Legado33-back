package br.com.legado33.app.reels.service;

import br.com.legado33.app.category.Category;
import br.com.legado33.app.category.dto.ReadCategoryDTO;
import br.com.legado33.app.category.exceptions.CategoryNotFoundException;
import br.com.legado33.app.category.service.CategoryService;
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
        //TODO -> Test sending a unreacheable category
            ReadCategoryDTO categoryDTO = categoryService.findCategoryById(reelDTO.category());
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
        if (reelDTO.title() != null && !reelDTO.title().equals(reel.getTitle())) {
            reel.setTitle(reelDTO.title());
        }
        if (reelDTO.description() != null && !reelDTO.description().equals(reel.getDescription())) {
            reel.setDescription(reelDTO.description());
        }
        if (reelDTO.category() != null) {
            //TODO -> Fazer verificação e tratamento de categoria não encontrada
            ReadCategoryDTO categoryDTO = categoryService.findCategoryById(reelDTO.category());
            Category category = new Category(categoryDTO);
            if (!category.equals(reel.getCategory())) {
                reel.setCategory(category);
            }
        }
        return reel;
    }

}