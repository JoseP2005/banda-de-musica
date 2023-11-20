package src.dominio;
import java.io.Serializable;
public class Socios extends Participantes implements Serializable {
    private static final long serialVersionUID = 1L;
    private

    public Socios(String nombre ){
        super(nombre);
    }

    @Override
    public String toString() {
        return "Director - Nombre: " + getNombre();
    }


}