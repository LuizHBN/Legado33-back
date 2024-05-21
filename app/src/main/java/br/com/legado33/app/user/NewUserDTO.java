package br.com.legado33.app.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotNull
        @NotBlank
        @Size(max = 510)
        String name,
        @NotBlank
        @NotNull
        @Email
        @Size(max = 1020)
        String mail
)  {

}
