package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.campaign.Campaign;
import br.com.legado33.app.domain.transaction.Transaction;
import br.com.legado33.app.domain.user.User;

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