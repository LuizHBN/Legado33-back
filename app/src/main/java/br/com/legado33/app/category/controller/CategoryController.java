package br.com.legado33.app.category.controller;
import br.com.legado33.app.category.dto.NewCategoryDTO;
import br.com.legado33.app.category.dto.ReadCategoryDTO;
import br.com.legado33.app.category.dto.UpdateCategoryDTO;
import br.com.legado33.app.category.exceptions.CategoryNotFoundException;
import br.com.legado33.app.category.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public ResponseEntity<ReadCategoryDTO> createCategory(@RequestBody @Valid NewCategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.saveNewCategory(categoryDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadCategoryDTO>> getAllCategories(Pageable page){
        return ResponseEntity.ok(categoryService.getAllCategories(page));
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
