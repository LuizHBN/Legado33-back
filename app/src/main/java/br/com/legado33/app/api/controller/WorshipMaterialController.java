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

import br.com.legado33.app.api.controller.dto.request.newDTO.NewWorshipMaterialDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateWorshipMaterialDTO;
import br.com.legado33.app.api.controller.dto.response.ReadWorshipMaterialDTO;
import br.com.legado33.app.domain.worshipMaterial.service.WorshipMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<Page<ReadWorshipMaterialDTO>> getAllWorshipMaterials(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="7") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(worshipMaterialService.getAllWorshipMaterials(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadWorshipMaterialDTO> getWorshipMaterialById(@PathVariable Long id) {
        return  ResponseEntity.ok(worshipMaterialService.findReadWorshipMaterialDTOById(id));
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
