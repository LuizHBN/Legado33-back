package br.com.legado33.app.campaign;

import br.com.legado33.app.campaign.dto.NewCampaignDTO;
import br.com.legado33.app.campaign.dto.ReadCampaignDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Campanha", schema = "legado33_mysql")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String title;

    @Column(name = "descricao", nullable = false, length = 1020)
    private String description;

    public Campaign(NewCampaignDTO campaignDTO) {
        this.title = campaignDTO.title();
        this.description = campaignDTO.description();
    }

    public Campaign(ReadCampaignDTO campaignDTO) {
        this.id = campaignDTO.id();
        this.title = campaignDTO.title();
        this.description = campaignDTO.description();
    }
}
