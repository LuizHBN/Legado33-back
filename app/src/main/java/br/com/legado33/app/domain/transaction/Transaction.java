package br.com.legado33.app.domain.transaction;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewTransactionDTO;
import br.com.legado33.app.domain.campaign.Campaign;
import br.com.legado33.app.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "id_campanha", referencedColumnName = "id", nullable = true)
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

    public Transaction(NewTransactionDTO transactionDTO) {
        this.value = transactionDTO.value();
        this.campaign = transactionDTO.campaign();
        this.type = transactionDTO.type();
        this.user = transactionDTO.user();
    }
}