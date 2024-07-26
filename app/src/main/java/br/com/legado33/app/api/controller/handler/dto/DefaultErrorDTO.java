package br.com.legado33.app.api.controller.handler.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultErrorDTO {
    private String message;
    private int status;

    public DefaultErrorDTO(String message, int status) {
        this.message = message;
        this.status = status;
    }


}
