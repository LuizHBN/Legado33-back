package br.com.legado33.app.reels.controller;

import br.com.legado33.app.reels.Reel;
import br.com.legado33.app.reels.service.ReelService;
import br.com.legado33.app.reels.dto.NewReelDTO;
import br.com.legado33.app.reels.dto.ReadReelDTO;
import br.com.legado33.app.reels.dto.UpdateReelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reels")
public class ReelController {

    private final ReelService reelService;

    @Autowired
    public ReelController(ReelService reelService) {
        this.reelService = reelService;
    }

    @PostMapping
    public ResponseEntity<Reel> createReel(@RequestBody NewReelDTO reelDTO) {
        return ResponseEntity.ok(reelService.saveNewReel(reelDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadReelDTO>> getAllReels(Pageable page) {
        return ResponseEntity.ok(reelService.getAllReels(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reel> getReelById(@PathVariable Long id) {
        return reelService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reel> updateReel(@PathVariable Long id, @RequestBody UpdateReelDTO reelDTO) {
        return ResponseEntity.ok(reelService.update(reelDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReel(@PathVariable Long id) {
        reelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}