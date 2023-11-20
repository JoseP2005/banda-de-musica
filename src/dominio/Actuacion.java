package src.dominio;

import java.io.Serializable;

public class Actuacion implements Serializable {

    private String nombre;

    private int numeroDeTelefono;

    public Contacto(String nombre, int numeroDeTelefono) {
        this.nombre = nombre;
        this.numeroDeTelefono = numeroDeTelefono;
    }
    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void setNumeroDeTelefono(int numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }

    public boolean equals (Object obj){
        Contacto c = (Contacto)obj;
        return nombre.equals(c.nombre);
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Numero de Telefono: " + numeroDeTelefono ;
    }
}