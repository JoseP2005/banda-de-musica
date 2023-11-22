package src.dominio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Actuacion implements Serializable {

    private String nombre;
    private LocalDateTime fecha;
    private List<Participante> participantes;

    public Actuacion(String nombre) {
        this.nombre = nombre;
        this.fecha = LocalDateTime.now();
        this.participantes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void agregarParticipante(Participante participante) {
        participantes.add(participante);
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    // Enhanced method to add multiple participants at once
    public void agregarParticipantes(List<Participante> nuevosParticipantes) {
        participantes.addAll(nuevosParticipantes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Actuacion actuacion = (Actuacion) obj;
        return nombre.equals(actuacion.nombre);
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Nombre: " + nombre + ", Fecha: " + fecha.format(formato);
    }
}
