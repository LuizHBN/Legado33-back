package br.com.legado33.app.user.dto;

import br.com.legado33.app.access.Access;
import br.com.legado33.app.user.User;

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