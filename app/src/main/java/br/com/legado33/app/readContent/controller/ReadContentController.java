package br.com.legado33.app.readContent.controller;

import br.com.legado33.app.category.exceptions.CategoryNotFoundException;
import br.com.legado33.app.readContent.dto.NewReadContentDTO;
import br.com.legado33.app.readContent.dto.ReadReadContentDTO;
import br.com.legado33.app.readContent.dto.UpdateReadContentDTO;
import br.com.legado33.app.readContent.exceptions.ReadContentNotFoundException;
import br.com.legado33.app.readContent.service.ReadContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/readContents")
@RequiredArgsConstructor
public class ReadContentController {
    private final ReadContentService readContentService;

    @PostMapping("/save")
    public ResponseEntity<ReadReadContentDTO> createReadContent(@RequestBody @Valid NewReadContentDTO readContentDTO) {
        try {
            return ResponseEntity.ok(readContentService.saveNewReadContent(readContentDTO));
        }catch (CategoryNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ReadReadContentDTO>> getAllReadContents(Pageable page) {
        return ResponseEntity.ok(readContentService.getAllReadContents(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadReadContentDTO> getReadContentById(@PathVariable Long id) {
        try{
            return  ResponseEntity.ok(readContentService.findReadContentById(id));
        }catch (ReadContentNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadReadContentDTO> updateReadContent(@PathVariable Long id, @Valid @RequestBody UpdateReadContentDTO readContentDTO) {
        if (readContentDTO == null){
            return ResponseEntity.notFound().build();
        }
        try {
            return ResponseEntity.ok(readContentService.update(readContentDTO, id));
        } catch (ReadContentNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReadContent(@PathVariable Long id) {
        try{
            readContentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(ReadContentNotFoundException e){
            return ResponseEntity.notFound().build();
        }
   }
}