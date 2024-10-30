package edu.acceso.xml_jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Modela un claustro de profesores.
 */
@JacksonXmlRootElement(localName = "claustro")
public class Claustro {

    @JacksonXmlProperty(isAttribute = true)
    private String centro;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "profesor")
    private Profesor[] profesores;

    public Claustro() { super(); }

    public Claustro cargarDatos(String centro, Profesor[] profesores) {
        setCentro(centro);
        setProfesores(profesores);
        return this;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public Profesor[] getProfesores() {
        return profesores;
    }

    public void setProfesores(Profesor[] profesores) {
        this.profesores = profesores;
    }
}
