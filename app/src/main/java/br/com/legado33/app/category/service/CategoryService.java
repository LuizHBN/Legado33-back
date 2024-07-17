package br.com.legado33.app.category.service;

import br.com.legado33.app.category.Category;
import br.com.legado33.app.category.dto.NewCategoryDTO;
import br.com.legado33.app.category.dto.ReadCategoryDTO;
import br.com.legado33.app.category.dto.UpdateCategoryDTO;
import br.com.legado33.app.category.exceptions.CategoryNotFoundException;
import br.com.legado33.app.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Page<ReadCategoryDTO> getAllCategories(Pageable page){
        return categoryRepository.findAll(page).map(ReadCategoryDTO :: new);
    }

    public ReadCategoryDTO saveNewCategory(NewCategoryDTO categoryDTO){
        Category category = new Category(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return new ReadCategoryDTO(savedCategory);
    }

    public ReadCategoryDTO findCategoryById(Long id){
        return categoryRepository.findById(id)
                .map(ReadCategoryDTO::new)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public ReadCategoryDTO update(UpdateCategoryDTO categoryDTO, Long id) {
        Category existingCategory= categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        existingCategory = updateCategoryFromDTO(categoryDTO, existingCategory);

        return new ReadCategoryDTO(categoryRepository.save(existingCategory));
    }

    public Category updateCategoryFromDTO(UpdateCategoryDTO categoryDTO,Category category) {
        if(!categoryDTO.title().equals(category.getTitle())){
            category.setTitle(categoryDTO.title());
        }
        return category;
    }

    public void delete(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.deleteById(id);
    }
}
