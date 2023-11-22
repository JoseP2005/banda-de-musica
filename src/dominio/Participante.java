package src.dominio;

import java.io.Serializable;

public class Participante implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;

    public Participante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

