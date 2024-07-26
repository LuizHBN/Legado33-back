package br.com.legado33.app.domain.message;

import br.com.legado33.app.domain.message.service.StatusConverter;
import br.com.legado33.app.domain.conversation.Conversation;
import br.com.legado33.app.api.controller.dto.request.newDTO.NewMessageDTO;
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

    public Message(NewMessageDTO messageDTO, Conversation conversation) {
        this.conversation = conversation;
        this.dateTime = messageDTO.dateTime();
        this.status = messageDTO.status();
    }

    //TODO -> Check if converter is working well

    public enum Status {
        SENT,
        RECEIVED,
        READ
    }
}