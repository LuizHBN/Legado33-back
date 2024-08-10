package br.com.legado33.app.domain.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.legado33.app.domain.access.Access;
public interface AccessRepository extends JpaRepository<Access, Long> {
}
