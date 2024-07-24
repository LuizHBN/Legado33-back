package br.com.legado33.app.worship.dto;

import br.com.legado33.app.worship.Worship;

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
