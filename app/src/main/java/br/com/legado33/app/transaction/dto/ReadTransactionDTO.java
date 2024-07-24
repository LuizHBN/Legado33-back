package br.com.legado33.app.transaction.dto;

import br.com.legado33.app.campaign.Campaign;
import br.com.legado33.app.transaction.Transaction;
import br.com.legado33.app.user.User;

public record ReadTransactionDTO(Long id,
                                 Double value,
                                 User user,
                                 Transaction.Type type,
                                 Campaign campaign) {
    public ReadTransactionDTO(Transaction transaction){
        this(transaction.getId(),
             transaction.getValue(),
             transaction.getUser(),
             transaction.getType(),
             transaction.getCampaign());
    }
}