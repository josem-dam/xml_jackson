package edu.acceso.xml_jackson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class Main {
    public static void main(String[] args) {
        Path archivo = Path.of("src", "main", "resources", "claustro.xml");

        Claustro claustro = new Claustro().cargarDatos("IES Castillo de Luna", new Profesor[] {
            new Profesor().cargarDatos("p01", null, null, "Pepe", "José", "Pérez Manrique", "Inglés"),
            new Profesor().cargarDatos("p12", null, null, "María", "María", "Galván Núñez", "Lengua y Literatura")
        });

        ObjectMapper mapper = new XmlMapper()
            .configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
            .enable(SerializationFeature.INDENT_OUTPUT);

        // Escritura.

        try(
            OutputStream st = Files.newOutputStream(archivo);
            OutputStreamWriter sw = new OutputStreamWriter(st);
        ) {
            mapper.writeValue(sw, claustro);
        }
        catch(IOException err) {
            err.printStackTrace();
            System.exit(1);
        }

        // Ejemplo de lectura.

        URI uri = null;
        try {
            uri = new URI("https://alacena.iescastillodeluna.es/anaquel/claustro.xml");
        }
        catch(URISyntaxException err) {
            err.printStackTrace();
            System.exit(1);
        }

        try (
            InputStream st = uri.toURL().openStream();
            InputStreamReader sr = new InputStreamReader(st);
        ) {
            Claustro otroClaustro = mapper.readValue(sr, Claustro.class);
            System.out.println(otroClaustro.getProfesores().length);
            System.out.println(otroClaustro.getProfesores()[1].getApelativo());
        }
        catch(IOException err) {
            err.printStackTrace();
            System.exit(1);
        }
    }
}