package br.com.legado33.app.user.dto;

import br.com.legado33.app.access.Access;
import jakarta.validation.constraints.NotNull;

public record UpdateUserAccessDTO(
        @NotNull
        Access access
) {
}
