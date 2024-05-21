package br.com.legado33.app.reels;

import br.com.legado33.app.category.Category;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Reel", schema = "legado33_mysql")
public class Reel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "link", nullable = false, length = 510)
    private String link;

    @Column(name = "titulo", length = 255)
    private String title;

    @Column(name = "descricao", length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;

    public Reel(NewReelDTO reelDTO) {
        this.link = reelDTO.link();
        this.title = reelDTO.title();
        this.description = reelDTO.description();
        this.category = reelDTO.category();
    }
}