package br.com.legado33.app.transaction;

import br.com.legado33.app.campaign.Campaign;
import br.com.legado33.app.transaction.dto.NewTransactionDTO;
import br.com.legado33.app.user.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transacao", schema = "legado33_mysql")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "valor", nullable = false)
    private Double value;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo", nullable = false)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "idCampanha", referencedColumnName = "id")
    private Campaign campaign;

    public Transaction(NewTransactionDTO transactionDTO, User user, Campaign campaign) {
        this.value = transactionDTO.value();
        this.type = transactionDTO.type();

        this.user = user;
        this.campaign = campaign;
    }

    public enum Type {
        OFERTA,
        DIZIMO
    }

    public Transaction(NewTransactionDTO transactionDTO){
        this.value = transactionDTO.value();
        this.campaign = transactionDTO.campaign();
        this.type = transactionDTO.type();
        this.user = transactionDTO.user();
    }
}