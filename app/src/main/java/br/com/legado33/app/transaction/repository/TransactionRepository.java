package br.com.legado33.app.transaction.repository;

import br.com.legado33.app.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
