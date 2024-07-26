package br.com.legado33.app.domain.worship;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewWorshipDTO;
import br.com.legado33.app.api.controller.dto.response.ReadWorshipDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Culto", schema = "legado33_mysql")
public class Worship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "horario", nullable = false)
    private LocalDateTime schedule;

    @Column(name = "local", nullable = false, length = 256)
    private String location;

    @Column(name = "duracao", nullable = false)
    private Integer duration;

    @Column(name = "tema", nullable = false, length = 256)
    private String theme;

    public Worship(NewWorshipDTO worshipDTO){
        this.schedule = worshipDTO.schedule();
        this.location = worshipDTO.location();
        this.duration = worshipDTO.duration();
        this.theme = worshipDTO.theme();
    }

    public Worship(ReadWorshipDTO worshipDTO) {
        this.schedule = worshipDTO.schedule();
        this.location = worshipDTO.location();
        this.duration = worshipDTO.duration();
        this.theme = worshipDTO.theme();
    }
}