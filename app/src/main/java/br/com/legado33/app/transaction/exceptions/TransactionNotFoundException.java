package br.com.legado33.app.transaction.exceptions;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Long id) {
        super("Transaction not found with id " + id);
    }
}
