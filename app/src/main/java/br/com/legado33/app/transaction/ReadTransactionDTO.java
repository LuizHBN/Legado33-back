package br.com.legado33.app.transaction;

import br.com.legado33.app.campaign.Campaign;
import br.com.legado33.app.user.ReadUserDTO;
import br.com.legado33.app.user.User;

public record ReadTransactionDTO(int id,
                                 double value,
                                 User user,
                                 Transaction.Type type,
                                 Campaign campaign) {

    public ReadTransactionDTO(Transaction transaction){
        this()
    }
}
// TODO -> continuar o Transaction.