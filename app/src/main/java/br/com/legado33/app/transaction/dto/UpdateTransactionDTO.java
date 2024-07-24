package br.com.legado33.app.transaction.dto;

import br.com.legado33.app.campaign.Campaign;
import br.com.legado33.app.transaction.Transaction;
import br.com.legado33.app.user.User;
import jakarta.validation.constraints.NotNull;

public record UpdateTransactionDTO(
        @NotNull
        Double value,
        @NotNull
        User user,
        @NotNull
        Transaction.Type type,
        @NotNull
        Campaign campaign
) {
}
