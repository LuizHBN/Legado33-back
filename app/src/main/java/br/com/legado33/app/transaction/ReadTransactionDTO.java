package br.com.legado33.app.transaction;

import br.com.legado33.app.campaign.Campaign;
import br.com.legado33.app.user.User;

public record ReadTransactionDTO(Long id,
                                 double value,
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