package br.com.legado33.app.domain.readContent.repository;

import br.com.legado33.app.domain.readContent.ReadContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadContentRepository extends JpaRepository<ReadContent,Long> {

}