package br.com.legado33.app.conversation;

import br.com.legado33.app.conversation.dto.NewConversationDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Conversa", schema = "legado33_mysql")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String title;

    @Column(name = "descricao", nullable = false, length = 1020)
    private String description;

    @Column(name = "imagem", length = 255)
    private String image;

    public Conversation(NewConversationDTO conversationDTO) {
        this.description = conversationDTO.description();
        this.title = conversationDTO.title();
        this.image = conversationDTO.image();
    }
}