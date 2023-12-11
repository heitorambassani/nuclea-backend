package br.com.heitor.nuclea.utils.serialize;

import br.com.heitor.nuclea.model.enums.Risco;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class RiscoDeserializer extends JsonDeserializer<Risco> {

    @Override
    public Risco deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();

        for (Risco risco : Risco.values()) {
            if (risco.name().equalsIgnoreCase(value)) {
                return risco;
            }
        }

        throw new IllegalArgumentException("Invalid 'risco' value provided: " + value);
    }
}