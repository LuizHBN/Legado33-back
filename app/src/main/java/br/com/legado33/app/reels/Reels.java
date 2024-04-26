
package br.com.legado33.app.reels;

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
@Table(name = "Reels", schema = "legado33_mysql")
public class Reels {
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
}