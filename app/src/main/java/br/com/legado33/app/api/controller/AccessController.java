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

import br.com.legado33.app.api.controller.dto.request.newDTO.NewAccessDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateAccessDTO;
import br.com.legado33.app.api.controller.dto.response.ReadAccessDTO;
import br.com.legado33.app.domain.access.service.AccessService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/acesso")
public class AccessController {

    private final AccessService accessService;

    public AccessController(AccessService service) {
        this.accessService = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ReadAccessDTO> createAccess(@RequestBody @Valid NewAccessDTO accessDTO) {
        return ResponseEntity.ok(accessService.saveNewAccess(accessDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadAccessDTO>> getAllAccess(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int size,
            @RequestParam(required = false) Long userId) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        if (userId != null) {
            return ResponseEntity.ok(accessService.getAllAccessesByUserId(userId, pageable));
        } else {
            return ResponseEntity.ok(accessService.getAllAccesses(pageable));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadAccessDTO> getAccessById(@PathVariable Long id) {
        return ResponseEntity.ok(accessService.findReadAccessDTOById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadAccessDTO> updateAccess(@PathVariable Long id, @Valid @RequestBody UpdateAccessDTO accessDTO) {
        if (accessDTO == null) {
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