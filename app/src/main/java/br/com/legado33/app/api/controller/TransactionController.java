package br.com.legado33.app.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewTransactionDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateTransactionDTO;
import br.com.legado33.app.api.controller.dto.response.ReadTransactionDTO;
import br.com.legado33.app.domain.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<Page<ReadTransactionDTO>> getAllTransactions(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="7") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(transactionService.getAllTransactions(pageable));
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
