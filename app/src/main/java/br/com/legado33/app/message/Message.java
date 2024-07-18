package br.com.legado33.app.message;

import br.com.legado33.app.conversation.Conversation;
import br.com.legado33.app.message.service.StatusConverter;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Mensagem", schema = "legado33_mysql")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_conversa", referencedColumnName = "id", nullable = false)
    private Conversation conversation;

    @Column(name = "data_hora", length = 45)
    private String dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status;
    //TODO -> Verificar funcionamento do converter

    public enum Status {
        SENT,
        RECEIVED,
        READ
    }
}