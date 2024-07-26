package br.com.legado33.app.api.controller.dto.request.updateDTO;

import br.com.legado33.app.domain.campaign.Campaign;
import br.com.legado33.app.domain.transaction.Transaction;
import br.com.legado33.app.domain.user.User;
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
