package br.com.legado33.app.user.repository;

import br.com.legado33.app.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
