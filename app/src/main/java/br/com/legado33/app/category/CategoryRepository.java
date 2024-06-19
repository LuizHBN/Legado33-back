package br.com.legado33.app.category;

import org.springframework.data.jpa.repository.JpaRepository;
//New Change

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
