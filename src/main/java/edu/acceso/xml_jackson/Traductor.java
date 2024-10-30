package edu.acceso.xml_jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.acceso.xml_jackson.Profesor.Departamento;

public class Traductor {

    public static class DepartamentoSerializer extends JsonSerializer<Profesor.Departamento> {

        @Override
        public void serialize(Departamento value, JsonGenerator gen, SerializerProvider sp) throws IOException {
           if(value == null) gen.writeNull();
           else gen.writeString(value.getNombre());
        }
    }

    public static class DepartamentoDeserializer extends JsonDeserializer<Profesor.Departamento> {
        @Override
        public Departamento deserialize(JsonParser parser, DeserializationContext context)
                throws IOException, JacksonException {
            return Departamento.obtenerDepartamento(parser.getText());
        }
    }
}