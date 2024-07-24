package br.com.legado33.app.worship.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateWorshipDTO(
        @NotNull
        LocalDateTime schedule,
        @NotNull
        @NotBlank
        String location,
        @NotNull
        Integer duration,
        @NotBlank
        @NotNull
        String theme
) {
}
