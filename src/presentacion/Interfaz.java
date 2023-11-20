package src.presentacion;

import dominio.Contacto;
import dominio.Libreta;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Interfaz implements Serializable {
    private static final long serialVersionUID = 1L;

    private Libreta libreta;
    private Scanner scanner;

    public Interfaz() {
        scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre de la Actuacion: ");
        String nombre = scanner.nextLine();

        libreta = Acto(nombre);
    }


    public void ejecutar() {
        cargarDatosCSV();
        boolean salir = false;
        while (!salir) {
            System.out.println("Ingrese una instrucción (mostrar, añadir, salir): ");
            String instruccion = scanner.nextLine();

            switch (instruccion.toLowerCase()) {
                case "mostrar":
                    mostrarActuacion();
                    break;
                case "añadir":
                    agregarActuacion();
                    break;
                case "salir":
                    salir = true;
                    guardarDatosCSV();
                    System.out.println("Programa terminado.");
                    break;
                default:
                    System.out.println("Error en la instrucción.");
                    break;
            }
        }
    }

    private void mostrarActuacion() {
        List<Contacto> contactos = libreta.getContactos();

        for (Contacto contacto : contactos) {
            System.out.println("Nombre: " + contacto.getNombre() + ", Número de Teléfono: " + contacto.getNumeroDeTelefono());
        }
        System.out.println("El número total de contactos es: " + libreta.contarContactos());
    }

    private void agregarContacto() {
        System.out.print("Ingrese el nombre del contacto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el número de teléfono del contacto: ");
        int numeroDeTelefono = Integer.parseInt(scanner.nextLine());

        Contacto nuevoContacto = new Contacto(nombre, numeroDeTelefono);
        libreta.agregarContacto(nuevoContacto);
        System.out.println("Contacto añadido correctamente.");
    }

    private void guardarDatosCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("libreta.csv"))) {
            writer.println("Nombre,Numero de Telefono");
            List<Contacto> contactos = libreta.getContactos();
            for (Contacto contacto : contactos) {
                writer.println(contacto.getNombre() + "," + contacto.getNumeroDeTelefono());
            }
            System.out.println("Datos guardados exitosamente en formato CSV.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos en formato CSV.");
        }
    }

    private void cargarDatosCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("libreta.csv"))) {
            // Saltamos la primera línea que contiene los encabezados
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nombre = parts[0];
                    int numeroDeTelefono = Integer.parseInt(parts[1]);
                    Contacto nuevoContacto = new Contacto(nombre, numeroDeTelefono);
                    libreta.agregarContacto(nuevoContacto);
                }
            }
            System.out.println("Datos cargados exitosamente desde formato CSV.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos desde formato CSV.");
        }
    }

}
