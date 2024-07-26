package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.worship.Worship;

import java.time.LocalDateTime;

public record ReadWorshipDTO(
        Long id,
        LocalDateTime schedule,
        String location,
        Integer duration,
        String theme
) {
    public ReadWorshipDTO(Worship worship){
        this(worship.getId(),
                worship.getSchedule(),
                worship.getLocation(),
                worship.getDuration(),
                worship.getTheme());
    }
}
