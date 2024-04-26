package br.com.legado33.app.user;

import br.com.legado33.app.access.Access;
import jakarta.persistence.*;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Usuario", schema = "legado33_mysql")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nome", nullable = false, length = 510)
    private String name;

    @Column(name = "mail", nullable = false, length = 1020)
    private String mail;

    @ManyToOne
    @JoinColumn(name = "id_acesso", referencedColumnName = "id")
    private Access access;
}