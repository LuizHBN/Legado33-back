package br.com.legado33.app.api.controller.dto.request.newDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewNewsDTO (
        @NotNull
        @NotBlank
        String title,
        @NotNull
        @NotBlank
        String description,
        @NotNull
        @NotBlank
        String image
){

}
