package br.com.legado33.app.access;

import jakarta.persistence.*;
import lombok.*;

//Teste Commit Gustavo

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Acesso", schema = "legado33_mysql")
public class Access {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    // Descrição do tipo de acesso
    @Column(name = "descricao", nullable = false)
    private String description;

    // Permissões de acesso
    @Column(name = "editar_culto", nullable = false)
    private boolean canEditWorship;

    @Column(name = "editar_reels", nullable = false)
    private boolean canEditReels;

    @Column(name = "editar_posts", nullable = false)
    private boolean canEditPosts;

    @Column(name = "acessar_financas", nullable = false)
    private boolean canAccessFinances;

    @Column(name = "enviar_notificacoes", nullable = false)
    private boolean canSendNotifications;

    @Column(name = "responder_mensagem", nullable = false)
    private boolean canReplyMessage;

    @Column(name = "editar_noticias", nullable = false)
    private boolean canEditNews;

    @Column(name = "editar_faq", nullable = false)
    private boolean canEditFaq;
}