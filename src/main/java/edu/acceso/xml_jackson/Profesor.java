package edu.acceso.xml_jackson;

import java.util.Arrays;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Modela los profesores del claustro.
 */
public class Profesor {

    public enum Departamento {
        LENGUA("Lengua y Literatura"),
        MATEMATICAS("Matemáticas"),
        INGLES("Inglés"),
        FRANCES("Francés"),
        CLASICAS("Cultura clásica"),
        FyQ("Física y Química"),
        ByG("Biología y Geología"),
        GyH("Geografía e Historia"),
        EDUCACIONFISICA("Educacion Física"),
        FILOSOFIA("Filosofía"),
        INFORMATICA("Informática"),
        ECONOMIA("Economía");

        /**
         * Nombre del departamento
         */
        private String nombre;

        Departamento(String nombre) {
            setNombre(nombre);
        }

        private void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }

        public static Departamento obtenerDepartamento(String nombre) {
            return Arrays.stream(Departamento.values())
                        .filter(d -> d.getNombre().equals(nombre))
                        .findFirst().orElse(null);
        }
    }

    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @JacksonXmlProperty(isAttribute = true)
    private String casillero;
    @JacksonXmlProperty(isAttribute = true)
    private String sustituye;

    private String apelativo;
    private String nombre;
    private String apellidos;

    @JsonSerialize(using=Traductor.DepartamentoSerializer.class)
    @JsonDeserialize(using=Traductor.DepartamentoDeserializer.class)
    private Departamento departamento;

    public Profesor() { super(); }

    public Profesor cargarDatos(String id,
                                String casillero,
                                String sustituye,
                                String apelativo,
                                String nombre,
                                String apellidos,
                                Departamento departamento) {
        setId(id);
        setSustituye(sustituye);
        setCasillero(casillero);
        setApelativo(apelativo);
        setNombre(nombre);
        setApellidos(apellidos);
        setDepartamento(departamento);
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCasillero() {
        return casillero;
    }

    public void setCasillero(String casillero) {
        this.casillero = casillero;
    }

    public String getSustituye() {
        return sustituye;
    }

    public void setSustituye(String sustituye) {
        this.sustituye = sustituye;
    }

    public String getApelativo() {
        return apelativo;
    }

    public void setApelativo(String apelativo) {
        this.apelativo = apelativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", apelativo, departamento);
    }
}
