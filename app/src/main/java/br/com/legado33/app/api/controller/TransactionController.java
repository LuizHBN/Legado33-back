package br.com.legado33.app.api.controller;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewTransactionDTO;
import br.com.legado33.app.api.controller.dto.response.ReadTransactionDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateTransactionDTO;
import br.com.legado33.app.domain.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/save")
    public ResponseEntity<ReadTransactionDTO> createTransaction(@RequestBody @Valid NewTransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.saveNewTransaction(transactionDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadTransactionDTO>> getAllTransactions(Pageable page) {
        return ResponseEntity.ok(transactionService.getAllTransactions(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadTransactionDTO> getTransactionById(@PathVariable Long id) {
        return  ResponseEntity.ok(transactionService.FindTransactionById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadTransactionDTO> updateTransaction(@PathVariable Long id, @Valid @RequestBody UpdateTransactionDTO transactionDTO) {
        if (transactionDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactionService.update(transactionDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
