package br.com.legado33.app.domain.transaction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.legado33.app.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public Page<Transaction> getAllTransactionsByUserId(Long userId, Pageable pageable);
}
