package src.dominio;

import java.util.ArrayList;
import java.util.List;

public class Banda {
    private String nombre;
    private List<Actuacion> actuaciones;

    public Banda(String nombre) {
        this.nombre = nombre;
        this.actuaciones = new ArrayList<>();
    }

    public String obtenerDatosCSV() {
        StringBuilder csvData = new StringBuilder();
        for (Actuacion actuacion : actuaciones) {
            csvData.append(actuacion.getNombre()).append(",").append(actuacion.getFecha()).append("\n");
        }
        return csvData.toString();
    }

    public void agregarActuacion(Actuacion actuacion) {
        actuaciones.add(actuacion);
    }

    public void borrar(Actuacion actuacion) {
        actuaciones.remove(actuacion);
    }

    public int contarActuaciones() {
        return actuaciones.size();
    }

    public List<Actuacion> getActuaciones() {
        return actuaciones;
    }

    public void agregarActuacion(Actuacion actuacion, List<Participante> participantes) {
        actuacion.getParticipantes().addAll(participantes);
        actuaciones.add(actuacion);
    }
    @Override
    public String toString () {
        return "Banda: " + nombre + ", Actuaciones: " + actuaciones + ", Total Actuaciones: " + contarActuaciones();
    }
}
