package br.com.legado33.app.domain.readContent;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewReadContentDTO;
import jakarta.persistence.*;
import lombok.*;
import br.com.legado33.app.domain.worshipMaterial.WorshipMaterial;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Material_Leitura", schema = "legado33_mysql")
public class ReadContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_material_culto", referencedColumnName = "id", nullable = false)
    private WorshipMaterial worshipMaterial;

    @Column(name = "livro", nullable = false, length = 255)
    private String book;

    @Column(name = "cap_inicial", nullable = false)
    private Integer initialChapter;

    @Column(name = "cap_final", nullable = false)
    private Integer finalChapter;

    @Column(name = "ver_inicial", nullable = false)
    private Integer initialVerse;

    @Column(name = "ver_final", nullable = false)
    private Integer finalVerse;

    @Column(name = "comentario", length = 1020)
    private String comment;

    public ReadContent(NewReadContentDTO readContentDTO, WorshipMaterial worshipMaterial) {
        this.worshipMaterial = worshipMaterial;
        this.book = readContentDTO.book();
        this.initialChapter = readContentDTO.initialChapter();
        this.finalChapter = readContentDTO.finalChapter();
        this.initialVerse = readContentDTO.initialVerse();
        this.finalVerse = readContentDTO.finalVerse();
        this.comment = readContentDTO.comment();
    }
}