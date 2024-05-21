package br.com.legado33.app.transaction;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

     public TransactionController(TransactionService transactionService){
         this.transactionService = transactionService;
     }
     @GetMapping
     public Page<ReadTransactionDTO> findAllTransactions(){
         transactionService.fin
     }
    @PostMapping
    public void createTransaction(@RequestBody @Valid NewTransactionDTO transactionDTO){
        transactionService.saveNewTransaction(transactionDTO);
    }

}
