package br.com.legado33.app.category.service.CategoryService;

import br.com.legado33.app.category.CategoryRepository;
import br.com.legado33.app.category.dto.CategoryService.ReadCategoryDTO;
import br.com.legado33.app.reels.dto.ReadReelDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public ReadCategoryDTO findById(Long id) {
        return  categoryRepository.findById(id)
                .map(ReadCategoryDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("category not found with id: " + id));
    }
}
