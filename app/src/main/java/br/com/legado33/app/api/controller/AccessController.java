package br.com.legado33.app.api.controller;

import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateAccessDTO;
import br.com.legado33.app.api.controller.dto.request.newDTO.NewAccessDTO;
import br.com.legado33.app.api.controller.dto.response.ReadAccessDTO;
import br.com.legado33.app.domain.access.service.AccessService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acesso")
public class AccessController {

    private final AccessService accessService;
    
    public AccessController(AccessService service){
        this.accessService = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ReadAccessDTO> createAccess(@RequestBody @Valid NewAccessDTO accessDTO){
        return ResponseEntity.ok(accessService.saveNewAccess(accessDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadAccessDTO>> getAllAccess(Pageable page){
        return ResponseEntity.ok(accessService.getAllAccesses(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadAccessDTO> getAccessById(@PathVariable Long id){
            return ResponseEntity.ok(accessService.findAccessById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadAccessDTO> updateAccess(@PathVariable Long id, @Valid @RequestBody UpdateAccessDTO accessDTO) {
        if (accessDTO == null){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(accessService.update(accessDTO, id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccess(@PathVariable Long id) {
            accessService.delete(id);
            return ResponseEntity.noContent().build();
    }

}



