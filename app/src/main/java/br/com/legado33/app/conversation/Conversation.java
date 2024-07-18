package br.com.legado33.app.conversation;

import br.com.legado33.app.conversation.dto.NewConversationDTO;
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
@Table(name = "Conversa", schema = "legado33_mysql")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_1", referencedColumnName = "id", nullable = false)
    private User user_1;

    @ManyToOne
    @JoinColumn(name = "usuario_2", referencedColumnName = "id", nullable = false)
    private User user_2;


    public Conversation(NewConversationDTO conversationDTO) {
        this.user_1 = conversationDTO.user_1();
        this.user_2 = conversationDTO.user_2();
    }
}