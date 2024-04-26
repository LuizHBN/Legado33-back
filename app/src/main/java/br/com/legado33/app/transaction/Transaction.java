package br.com.legado33.app.transaction;

import br.com.legado33.app.campaign.Campaign;
import jakarta.persistence.*;
import lombok.*;
import br.com.legado33.app.user.User;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Transacao", schema = "legado33_mysql")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "Remetente", nullable = false)
    private Integer sender;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo", nullable = false)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "idCampanha")
    private Campaign campaign;

    @Column(name = "Transferenciacol", length = 45)
    private String transferColumn;

    public enum Type {
        OFFERING,
        TITHE
    }
}
