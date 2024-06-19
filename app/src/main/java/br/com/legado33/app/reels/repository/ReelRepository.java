package br.com.legado33.app.reels.repository;

import br.com.legado33.app.reels.Reel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReelRepository extends JpaRepository<Reel,Long> {
}
