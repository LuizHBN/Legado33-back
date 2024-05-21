package br.com.legado33.app.transaction;

import br.com.legado33.app.campaign.Campaign;
import br.com.legado33.app.user.User;

public record NewTransactionDTO (
                                 double value,
                                 User user,
                                 Transaction.Type type,
                                 Campaign campaign
){
}
