package br.com.legado33.app.transaction.service;

import br.com.legado33.app.transaction.dto.NewTransactionDTO;
import br.com.legado33.app.transaction.dto.ReadTransactionDTO;
import br.com.legado33.app.transaction.Transaction;
import br.com.legado33.app.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

   private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public void saveNewTransaction(NewTransactionDTO transactionDTO) {
        Transaction newTrasaction = new Transaction(transactionDTO);
        transactionRepository.save(newTrasaction);
    }

    public Page<ReadTransactionDTO> getAllTransactions(Pageable page){
        return transactionRepository.findAll(page).map(ReadTransactionDTO::new);
    }
}
