package src.dominio;
public abstract class Participantes {
    private String nombre;
    private double pago;
    private int num_socios;

    public Participantes(String nombre ) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }
    public int getNumSocios() {
        return num_socios;
    }

    public void setNumSocios(int num_socios) {
        this.num_socios = num_socios;
    }

}