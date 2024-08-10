package br.com.legado33.app.api.controller.dto.request.newDTO;

import br.com.legado33.app.domain.campaign.Campaign;
import br.com.legado33.app.domain.transaction.Transaction;
import br.com.legado33.app.domain.user.User;
import jakarta.validation.constraints.NotNull;

public record NewTransactionDTO (
        @NotNull
        Double value,
        @NotNull
        User user,
        @NotNull
        Transaction.Type type,
        Campaign campaign
){
}
