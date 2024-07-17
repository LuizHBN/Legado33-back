package br.com.legado33.app.access.repository;

import br.com.legado33.app.access.Access;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepository extends JpaRepository<Access, Long>{
}
