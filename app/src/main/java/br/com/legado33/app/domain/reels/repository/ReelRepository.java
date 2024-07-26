package br.com.legado33.app.domain.reels.repository;

import br.com.legado33.app.domain.reels.Reel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReelRepository extends JpaRepository<Reel,Long> {
}
