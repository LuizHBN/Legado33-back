package br.com.legado33.app.reels.controller;

import br.com.legado33.app.reels.service.ReelService;
import br.com.legado33.app.reels.dto.NewReelDTO;
import br.com.legado33.app.reels.dto.ReadReelDTO;
import br.com.legado33.app.reels.dto.UpdateReelDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<ReadReelDTO>> getAllReels(Pageable page) {
        return ResponseEntity.ok(reelService.getAllReels(page));
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