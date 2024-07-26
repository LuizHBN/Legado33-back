package br.com.legado33.app.domain.worshipMaterial;

import br.com.legado33.app.domain.worship.Worship;
import br.com.legado33.app.api.controller.dto.request.newDTO.NewWorshipMaterialDTO;
import br.com.legado33.app.api.controller.dto.response.ReadWorshipMaterialDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Material_Culto", schema = "legado33_mysql")
public class WorshipMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_culto", referencedColumnName = "id", nullable = false)
    private Worship worship;

    @Column(name = "descricao", nullable = false, length = 510)
    private String description;

    @Column(name = "midia", length = 255)
    private String media;

    @Column(name = "comentario", nullable = false, length = 1020)
    private String comment;

    public WorshipMaterial(NewWorshipMaterialDTO worshipMaterialDTO, Worship worship) {
        this.worship = worship;
        this.description = worshipMaterialDTO.description();
        this.media = worshipMaterialDTO.media();
        this.comment = worshipMaterialDTO.comment();
    }

    public WorshipMaterial(ReadWorshipMaterialDTO worshipMaterialDTO) {
        this.worship = worship;
        this.description = worshipMaterialDTO.description();
        this.media = worshipMaterialDTO.media();
        this.comment = worshipMaterialDTO.comment();
    }
}