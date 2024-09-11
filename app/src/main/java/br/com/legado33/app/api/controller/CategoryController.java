package br.com.legado33.app.api.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import br.com.legado33.app.api.controller.dto.request.newDTO.NewCategoryDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateCategoryDTO;
import br.com.legado33.app.api.controller.dto.response.ReadCategoryDTO;
import br.com.legado33.app.domain.category.service.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public ResponseEntity<ReadCategoryDTO> createCategory(@RequestBody @Valid NewCategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.saveNewCategory(categoryDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadCategoryDTO>> getAllCategories(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="7") int size){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(categoryService.getAllCategories(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadCategoryDTO> getCategoryById(@PathVariable Long id){
            return ResponseEntity.ok(categoryService.findCategoryById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadCategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody UpdateCategoryDTO categoryDTO) {
        if (categoryDTO == null){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(categoryService.update(categoryDTO, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
            categoryService.delete(id);
            return ResponseEntity.noContent().build();

    }


}
