package br.com.legado33.app.message.repository;

import br.com.legado33.app.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
