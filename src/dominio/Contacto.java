package src.dominio;
import java.util.ArrayList;
import java.util.List;
public class Libreta {
    private String nombre;
    private List<Actos> actuaciones;

    public Libreta(String nombre) {
        this.nombre = nombre;
        actuaciones = new ArrayList<>();
    }

    public String obtenerDatosCSV() {
        StringBuilder csvData = new StringBuilder();
        for (Con contacto : contactos) {
            csvData.append(contacto.getNombre()).append(",").append(contacto.getNumeroDeTelefono()).append("\n");
        }
        return csvData.toString();
    }

    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
    }

    public void borrar(Contacto contacto){
        contactos.remove(contacto);
    }


    public int contarContactos() {
        return contactos.size();
    }

    public List<Contacto> getContactos() {
        return contactos;
    }



    @Override
    public String toString() {
        return "Libreta: " + nombre + " Contactos: "+ contactos + ", Total Contactos: " + contarContactos();
    }
}