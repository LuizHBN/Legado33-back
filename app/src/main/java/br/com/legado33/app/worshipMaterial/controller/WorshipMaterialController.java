package br.com.legado33.app.worshipMaterial.controller;

import br.com.legado33.app.worshipMaterial.dto.NewWorshipMaterialDTO;
import br.com.legado33.app.worshipMaterial.dto.ReadWorshipMaterialDTO;
import br.com.legado33.app.worshipMaterial.dto.UpdateWorshipMaterialDTO;
import br.com.legado33.app.worshipMaterial.service.WorshipMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materialCulto")
@RequiredArgsConstructor
public class WorshipMaterialController {
    private final WorshipMaterialService worshipMaterialService;

    @PostMapping("/save")
    public ResponseEntity<ReadWorshipMaterialDTO> createWorshipMaterial(@RequestBody @Valid NewWorshipMaterialDTO worshipMaterialDTO) {
        return ResponseEntity.ok(worshipMaterialService.saveNewWorshipMaterial(worshipMaterialDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadWorshipMaterialDTO>> getAllWorshipMaterials(Pageable page) {
        return ResponseEntity.ok(worshipMaterialService.getAllWorshipMaterials(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadWorshipMaterialDTO> getWorshipMaterialById(@PathVariable Long id) {
        return  ResponseEntity.ok(worshipMaterialService.findWorshipMaterialById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadWorshipMaterialDTO> updateWorshipMaterial(@PathVariable Long id, @Valid @RequestBody UpdateWorshipMaterialDTO worshipMaterialDTO) {
        if (worshipMaterialDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(worshipMaterialService.update(worshipMaterialDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorshipMaterial(@PathVariable Long id) {
        worshipMaterialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
