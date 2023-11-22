package src.dominio;
import java.io.Serializable;

public class Musico extends Participante implements Serializable {
    private static final long serialVersionUID = 1L;
    private double pago;

    public Musico(String nombre, double pago){
        super(nombre);
        this.pago = pago;
    }

    public double getPago() {
        return pago;
    }

    public void SetPago(double pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return "Musico - Nombre: " + getNombre() + ", Pago: " + getPago();
    }
}