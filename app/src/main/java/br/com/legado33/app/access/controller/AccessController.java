package br.com.legado33.app.access.controller;

import br.com.legado33.app.access.dto.UpdateAccessDTO;
import br.com.legado33.app.access.exceptions.AccessNotFoundException;
import br.com.legado33.app.access.dto.NewAccessDTO;
import br.com.legado33.app.access.dto.ReadAccessDTO;
import br.com.legado33.app.access.service.AccessService;
import jakarta.persistence.EntityNotFoundException;
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
        try {
            return ResponseEntity.ok(accessService.findAccessById(id));
        } catch (EntityNotFoundException e) {
               return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadAccessDTO> updateAccess(@PathVariable Long id, @Valid @RequestBody UpdateAccessDTO accessDTO) {
        if (accessDTO == null){
            return ResponseEntity.notFound().build();
        }
        try {
            return ResponseEntity.ok(accessService.update(accessDTO, id));
        } catch (AccessNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccess(@PathVariable Long id) {
        try{
            accessService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(AccessNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}



