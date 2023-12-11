
package br.com.heitor.nuclea.utils.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;

public class CustomLocalDateDeserialize extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = 1L;

    public CustomLocalDateDeserialize() {
        super(LocalDate.class);
    }


    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDate.parse(p.getValueAsString()); // or overloaded with an appropriate format
    }
}