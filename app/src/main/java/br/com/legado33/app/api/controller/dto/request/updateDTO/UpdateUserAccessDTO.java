package br.com.legado33.app.api.controller.dto.request.updateDTO;

import br.com.legado33.app.domain.access.Access;
import jakarta.validation.constraints.NotNull;

public record UpdateUserAccessDTO(
        @NotNull
        Access access
) {
}
