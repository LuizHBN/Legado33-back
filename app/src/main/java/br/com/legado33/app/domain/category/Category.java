package br.com.legado33.app.domain.category;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewCategoryDTO;
import br.com.legado33.app.api.controller.dto.response.ReadCategoryDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Categoria", schema = "legado33_mysql")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String title;


    public Category(ReadCategoryDTO categoryDTO){
        this.id = categoryDTO.id();
        this.title = categoryDTO.title();
    }

    public Category(NewCategoryDTO categoryDTO) {
        this.title = categoryDTO.title();
    }
}