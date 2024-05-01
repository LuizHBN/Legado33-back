package br.com.legado33.app.user;

import br.com.legado33.app.access.Access;

public record ReadUserDTO(
        Long id,
        String name,
        String mail,
        Access access
) {
    public ReadUserDTO(User user){
        this(user.getId(), user.getName(), user.getMail(), user.getAccess());
    }
}
