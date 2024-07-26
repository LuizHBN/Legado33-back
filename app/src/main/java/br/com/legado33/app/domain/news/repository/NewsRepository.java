package br.com.legado33.app.domain.news.repository;

import br.com.legado33.app.domain.news.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
