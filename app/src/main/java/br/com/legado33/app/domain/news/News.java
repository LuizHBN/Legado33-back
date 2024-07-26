package br.com.legado33.app.domain.news;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewNewsDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Novidade", schema = "legado33_mysql")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String title;

    @Column(name = "descricao", nullable = false, length = 1020)
    private String description;

    @Column(name = "imagem", length = 255)
    private String image;

    public News(NewNewsDTO newsDTO) {
        this.description = newsDTO.description();
        this.image = newsDTO.image();
        this.title = newsDTO.title();
    }
}