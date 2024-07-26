package br.com.legado33.app.domain.transaction.exception;

import br.com.legado33.app.api.controller.handler.NotFoundException;

public class TransactionNotFoundException extends NotFoundException{
    public TransactionNotFoundException(Long id) {
        super("Transaction not found with id " + id);
    }
}
