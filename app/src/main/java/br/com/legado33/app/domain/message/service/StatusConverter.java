package br.com.legado33.app.domain.message.service;

import br.com.legado33.app.domain.message.Message;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Message.Status, String> {

    @Override
    public String convertToDatabaseColumn(Message.Status status) {
        switch (status) {
            case SENT:
                return "ENVIADA";
            case RECEIVED:
                return "RECEBIDA";
            case READ:
                return "LIDA";
            default:
                throw new IllegalArgumentException("Unknown status: " + status);
        }
    }

    @Override
    public Message.Status convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "ENVIADA":
                return Message.Status .SENT;
            case "RECEBIDA":
                return Message.Status .RECEIVED;
            case "LIDA":
                return Message.Status .READ;
            default:
                throw new IllegalArgumentException("Unknown dbData: " + dbData);
        }
    }
}