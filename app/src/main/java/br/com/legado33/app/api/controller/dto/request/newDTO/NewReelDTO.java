package br.com.legado33.app.api.controller.dto.request.newDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewReelDTO(
    @NotNull
    @NotBlank
    @Size(max = 510)
    String link,

    @Size(max = 255)
    String title,

    @Size(max = 255)
    String description,

    @NotNull
    Long category
) {

}