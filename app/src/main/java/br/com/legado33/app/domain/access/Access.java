package br.com.legado33.app.domain.access;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewAccessDTO;
import br.com.legado33.app.api.controller.dto.response.ReadAccessDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Access(NewAccessDTO accessDTO){
        this.description = accessDTO.description();
        this.canEditWorship = accessDTO.canEditWorship();
        this.canEditReels = accessDTO.canEditReels();
        this.canEditPosts = accessDTO.canEditPosts();
        this.canAccessFinances = accessDTO.canAccessFinances();
        this.canSendNotifications = accessDTO.canSendNotifications();
        this.canReplyMessage = accessDTO.canReplyMessage();
        this.canEditNews = accessDTO.canEditNews();
        this.canEditFaq = accessDTO.canEditFaq();
    }

    public Access(ReadAccessDTO accessDTO) {
        this.description = accessDTO.description();
        this.canEditWorship = accessDTO.canEditWorship();
        this.canEditReels = accessDTO.canEditReels();
        this.canEditPosts = accessDTO.canEditPosts();
        this.canAccessFinances = accessDTO.canAccessFinances();
        this.canSendNotifications = accessDTO.canSendNotifications();
        this.canReplyMessage = accessDTO.canReplyMessage();
        this.canEditNews = accessDTO.canEditNews();
        this.canEditFaq = accessDTO.canEditFaq();
    }
}