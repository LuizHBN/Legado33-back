package br.com.legado33.app.conversation.repository;

import br.com.legado33.app.conversation.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation,Long> {
}
