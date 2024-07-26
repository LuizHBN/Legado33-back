package br.com.legado33.app.api.controller;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewWorshipDTO;
import br.com.legado33.app.api.controller.dto.response.ReadWorshipDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateWorshipDTO;
import br.com.legado33.app.domain.worship.service.WorshipService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/culto")
public class WorshipController {

    private final WorshipService worshipService;

    public WorshipController(WorshipService service){
        this.worshipService = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ReadWorshipDTO> createWorship(@RequestBody @Valid NewWorshipDTO worshipDTO){
        return ResponseEntity.ok(worshipService.saveNewWorship(worshipDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadWorshipDTO>> getAllWorship(Pageable page){
        return ResponseEntity.ok(worshipService.getAllWorshipes(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadWorshipDTO> getWorshipById(@PathVariable Long id){
        return ResponseEntity.ok(worshipService.findWorshipById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadWorshipDTO> updateWorship(@PathVariable Long id, @Valid @RequestBody UpdateWorshipDTO worshipDTO) {
        if (worshipDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(worshipService.update(worshipDTO, id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorship(@PathVariable Long id) {
        worshipService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
