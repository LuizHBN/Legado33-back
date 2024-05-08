package br.com.legado33.app.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewUserDTO(
        @NotNull
        @NotBlank
        String name,
        @NotBlank
        @NotNull
        @Email
        String mail
)  {

}
