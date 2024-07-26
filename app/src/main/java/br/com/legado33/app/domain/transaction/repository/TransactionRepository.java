package br.com.legado33.app.domain.transaction.repository;

import br.com.legado33.app.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
