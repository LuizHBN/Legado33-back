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

import br.com.legado33.app.api.controller.dto.request.newDTO.NewReelDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateReelDTO;
import br.com.legado33.app.api.controller.dto.response.ReadReelDTO;
import br.com.legado33.app.domain.reels.service.ReelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reels")
@RequiredArgsConstructor
public class ReelController {
    private final ReelService reelService;

    @PostMapping("/save")
    public ResponseEntity<ReadReelDTO> createReel(@RequestBody @Valid NewReelDTO reelDTO) {
            return ResponseEntity.ok(reelService.saveNewReel(reelDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadReelDTO>> getAllReels(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="7") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(reelService.getAllReels(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadReelDTO> getReelById(@PathVariable Long id) {
            return  ResponseEntity.ok(reelService.findReelById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadReelDTO> updateReel(@PathVariable Long id, @Valid @RequestBody UpdateReelDTO reelDTO) {
        if (reelDTO == null){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(reelService.update(reelDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReel(@PathVariable Long id) {
            reelService.delete(id);
            return ResponseEntity.noContent().build();
    }
}