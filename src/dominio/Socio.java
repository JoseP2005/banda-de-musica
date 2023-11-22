package src.dominio;

import java.io.Serializable;

public class Socio extends Participante implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numSocios;

    public Socio(String nombre, int numSocios){
        super(nombre);
        this.numSocios = numSocios;
    }

    public int getNumSocios() {
        return numSocios;
    }

    public void SetNumSocios(int numSocios) {
        this.numSocios = numSocios;
    }

    @Override
    public String toString() {
        return "Socio - Nombre: " + getNombre() + ", Numero de socios: " + getNumSocios();
    }
}