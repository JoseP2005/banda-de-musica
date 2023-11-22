package src.dominio;

import java.io.Serializable;

public class Director extends Participante implements Serializable {
    private static final long serialVersionUID = 1L;

    public Director(String nombre) {
        super(nombre);
    }

    @Override
    public String toString() {
        return "Director - Nombre: " + getNombre();
    }
}
