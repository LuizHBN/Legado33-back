package br.com.legado33.app.reels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReelService {

    private final ReelRepository reelRepository;

    @Autowired
    public ReelService(ReelRepository reelRepository) {
        this.reelRepository = reelRepository;
    }

    public Reel saveNewReel(NewReelDTO reelDTO) {
        return reelRepository.save(new Reel(reelDTO));
    }

    public Page<ReadReelDTO> getAllReels(Pageable page) {
        return reelRepository.findAll(page).map(ReadReelDTO :: new);
    }

    public Optional<Reel> findById(Long id) {
        return reelRepository.findById(id);
    }

    public Reel update(UpdateReelDTO reel) {
        Reel existingReel = reelRepository.findById(reel.id()).orElseThrow(RuntimeException::new);
        existingReel.setTitle(reel.title());
        existingReel.setDescription(reel.description());
        existingReel.setCategory(reel.category());
        return reelRepository.save(existingReel);
    }

    public void delete(Long id) {
        reelRepository.deleteById(id);
    }
}