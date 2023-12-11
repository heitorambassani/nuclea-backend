package br.com.heitor.nuclea.utils.serialize;

import br.com.heitor.nuclea.model.enums.StatusProjeto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StatusProjetoDeserializer extends JsonDeserializer<StatusProjeto> {

    @Override
    public StatusProjeto deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();

        for (StatusProjeto statusProjeto : StatusProjeto.values()) {
            if (statusProjeto.name().equalsIgnoreCase(value)) {
                return statusProjeto;
            }
        }

        throw new IllegalArgumentException("Invalid 'statusProjeto' value provided: " + value);
    }
}