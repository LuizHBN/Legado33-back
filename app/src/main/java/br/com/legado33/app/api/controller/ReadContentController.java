package br.com.legado33.app.api.controller;

import org.springframework.data.domain.Page;
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

import br.com.legado33.app.api.controller.dto.request.newDTO.NewReadContentDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateReadContentDTO;
import br.com.legado33.app.api.controller.dto.response.ReadReadContentDTO;
import br.com.legado33.app.domain.category.exception.CategoryNotFoundException;
import br.com.legado33.app.domain.readContent.exception.ReadContentNotFoundException;
import br.com.legado33.app.domain.readContent.service.ReadContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/material_leitura")
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
    public ResponseEntity<Page<ReadReadContentDTO>> getAllReadContents(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="7") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(readContentService.getAllReadContents(pageable));
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