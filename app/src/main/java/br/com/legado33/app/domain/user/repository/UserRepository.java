package br.com.legado33.app.domain.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.legado33.app.domain.access.Access;
import br.com.legado33.app.domain.user.User;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT DISTINCT u.access FROM User u WHERE u.id = :userId")
    Page<Access> findDistinctAccessByUserId(@Param("userId") Long userId, Pageable pageable);
}
