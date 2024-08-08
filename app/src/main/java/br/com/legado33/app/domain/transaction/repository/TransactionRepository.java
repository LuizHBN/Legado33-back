package br.com.legado33.app.domain.transaction.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.legado33.app.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    public Page<Optional<Transaction>> findByUserId(Long userId);
}
