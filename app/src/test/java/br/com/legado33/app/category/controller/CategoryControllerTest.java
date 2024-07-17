package br.com.legado33.app.category.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.legado33.app.category.dto.NewCategoryDTO;
import br.com.legado33.app.category.dto.ReadCategoryDTO;
import br.com.legado33.app.category.dto.UpdateCategoryDTO;
import br.com.legado33.app.category.service.CategoryService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

// Classe de teste para CategoryController
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCategory() {
        NewCategoryDTO newCategoryDTO = new NewCategoryDTO("Categoria Teste"); // Valor substituído
        ReadCategoryDTO readCategoryDTO = new ReadCategoryDTO(1L, "Categoria Teste"); // Valor substituído
        when(categoryService.saveNewCategory(any(NewCategoryDTO.class))).thenReturn(readCategoryDTO);

        ResponseEntity<ReadCategoryDTO> response = categoryController.createCategory(newCategoryDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readCategoryDTO, response.getBody());
        verify(categoryService, times(1)).saveNewCategory(any(NewCategoryDTO.class));
    }

    @SuppressWarnings("null")
    @Test
    void testGetAllCategories() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<ReadCategoryDTO> page = new PageImpl<>(Collections.singletonList(new ReadCategoryDTO(1L, "Categoria Teste"))); // Valor substituído
        when(categoryService.getAllCategories(any(PageRequest.class))).thenReturn(page);

        ResponseEntity<Page<ReadCategoryDTO>> response = categoryController.getAllCategories(pageRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getContent().size());
        verify(categoryService, times(1)).getAllCategories(any(PageRequest.class));
    }

    @Test
    void testGetCategoryById() {
        Long id = 1L; // Valor mantido
        ReadCategoryDTO readCategoryDTO = new ReadCategoryDTO(1L, "Categoria Teste"); // Valor substituído
        when(categoryService.findCategoryById(id)).thenReturn(readCategoryDTO);

        ResponseEntity<ReadCategoryDTO> response = categoryController.getCategoryById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readCategoryDTO, response.getBody());
        verify(categoryService, times(1)).findCategoryById(id);
    }

    @Test
    void testUpdateCategory() {
        Long id = 1L; // Valor mantido
        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO("Categoria Atualizada"); // Valor substituído
        ReadCategoryDTO readCategoryDTO = new ReadCategoryDTO(1L, "Categoria Atualizada"); // Valor substituído
        when(categoryService.update(any(UpdateCategoryDTO.class), eq(id))).thenReturn(readCategoryDTO);

        ResponseEntity<ReadCategoryDTO> response = categoryController.updateCategory(id, updateCategoryDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readCategoryDTO, response.getBody());
        verify(categoryService, times(1)).update(any(UpdateCategoryDTO.class), eq(id));
    }

    @Test
    void testDeleteCategory() {
        Long id = 1L; // Valor mantido
        doNothing().when(categoryService).delete(id);

        ResponseEntity<Void> response = categoryController.deleteCategory(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).delete(id);
    }
}
