package br.com.legado33.app.api.controller.dto.response;

import br.com.legado33.app.domain.access.Access;
import br.com.legado33.app.domain.user.User;

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