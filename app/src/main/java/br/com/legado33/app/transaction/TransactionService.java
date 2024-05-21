package br.com.legado33.app.transaction;

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
        transactionRepository.findAll(page).map(new ::)
    }
}
