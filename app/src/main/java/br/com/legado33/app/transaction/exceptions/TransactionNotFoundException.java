package br.com.legado33.app.transaction.exceptions;

import br.com.legado33.app.exceptions.NotFoundException;

public class TransactionNotFoundException extends NotFoundException{
    public TransactionNotFoundException(Long id) {
        super("Transaction not found with id " + id);
    }
}
