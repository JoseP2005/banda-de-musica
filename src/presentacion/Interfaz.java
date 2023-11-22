package src.presentacion;

import src.dominio.*;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;


public class Interfaz {
    private Banda banda;
    private Scanner scanner;

    public Interfaz() {
        scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre de la banda: ");
        String nombre = scanner.nextLine();

        banda = new Banda(nombre);
    }

    public void ejecutar() {
        cargarDatosCSV();
        boolean salir = false;
        while (!salir) {
            System.out.println("Ingrese una instrucción (mostrar, añadir, borrar, salir): ");
            String instruccion = scanner.nextLine();

            switch (instruccion.toLowerCase()) {
                case "mostrar":
                    mostrarActuaciones();
                    break;
                case "añadir":
                    agregarActuacion();
                    agregarDirector();
                    agregarSocios();
                    agregarMusicos();
                    break;
                case "borrar":
                    System.out.print("Ingrese el nombre de la actuacion a borrar: ");
                    String nombre = scanner.nextLine();
                    Actuacion actuacion = new Actuacion(nombre);
                    banda.borrar(actuacion);
                    System.out.println("Actuacion borrada correctamente.");
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

    private void mostrarActuaciones() {
        List<Actuacion> actuaciones = banda.getActuaciones();

        for (Actuacion actuacion : actuaciones) {
            System.out.println("Nombre: " + actuacion.getNombre() + ", Fecha: " + actuacion.getFecha());

            // Display participants
            List<Participante> participantes = actuacion.getParticipantes();
            System.out.println("Participantes:");
            for (Participante participante : participantes) {
                if (participante instanceof Director) {
                    System.out.println("  - Director: " + participante.getNombre());
                } else if (participante instanceof Socio) {
                    System.out.println("  - Socio: " + participante.getNombre() + ", Número de socios: " + ((Socio) participante).getNumSocios());
                } else if (participante instanceof Musico) {
                    System.out.println("  - Musico: " + participante.getNombre() + ", Pago: " + ((Musico) participante).getPago());
                }
            }
        }

        System.out.println("El número total de Actuaciones es: " + banda.contarActuaciones());
    }
    private void agregarActuacion() {
        System.out.print("Ingrese el nombre de la actuacion: ");
        String nombre = scanner.nextLine();

        Actuacion nuevaActuacion = new Actuacion(nombre);

        // Obtener y asignar participantes
        List<Participante> participantes = new ArrayList<>();

        participantes.addAll(agregarDirector());
        participantes.addAll(agregarSocios());
        participantes.addAll(agregarMusicos());

        nuevaActuacion.agregarParticipantes(participantes);

        banda.agregarActuacion(nuevaActuacion);
        System.out.println("Actuacion añadida correctamente.");
    }

    private Director obtenerDirector() {
        System.out.print("Ingrese el nombre del Director: ");
        String nombreDirector = scanner.nextLine();
        return new Director(nombreDirector);
    }

    private List<Participante> agregarDirector() {
        System.out.print("Ingrese la cantidad de Directores que desea crear: ");
        int cantidadDirectores = scanner.nextInt();
        scanner.nextLine();

        List<Participante> directores = new ArrayList<>();

        for (int i = 0; i < cantidadDirectores; i++) {
            System.out.println("--- CREANDO DIRECTOR " + (i + 1) + " ---");
            System.out.print("Ingrese el nombre del Director: ");
            String nombreDirector = scanner.nextLine();

            Director director = obtenerDirector(); // Implement this method

            // Add the Director to the List
            directores.add(director);
        }

        return directores;
    }

    private List<Participante> agregarSocios() {
        System.out.print("Ingrese la cantidad de socios que desea agregar: ");
        int cantidadSocios = scanner.nextInt();
        scanner.nextLine();

        List<Participante> socios = new ArrayList<>();

        for (int j = 0; j < cantidadSocios; j++) {
            System.out.println("--- AGREGANDO SOCIO " + (j + 1) + " ---");
            System.out.print("Ingrese el nombre del Socio: ");
            String nombreSocio = scanner.nextLine();
            System.out.print("Ingrese el número de socios: ");
            int numSocios = scanner.nextInt();
            scanner.nextLine();

            Participante socio = new Socio(nombreSocio, numSocios);


            socios.add(socio);
        }

        return socios;
    }

    private List<Participante> agregarMusicos() {
        System.out.print("Ingrese la cantidad de Musicos que desea agregar: ");
        int cantidadMusicos = scanner.nextInt();
        scanner.nextLine();

        List<Participante> musicos = new ArrayList<>();

        for (int k = 0; k < cantidadMusicos; k++) {
            System.out.println("--- AGREGANDO MUSICO " + (k + 1) + " ---");
            System.out.print("Ingrese el nombre del musico: ");
            String nombreMusico = scanner.nextLine();
            System.out.print("Ingrese el pago del musico: ");
            double pagoMusico = scanner.nextDouble();
            scanner.nextLine();

            Participante musico = new Musico(nombreMusico, pagoMusico); // Implement this constructor

            // Add the Musico to the List
            musicos.add(musico);
        }

        return musicos;
    }

    private void guardarDatosCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("banda.csv"))) {
            writer.println("Nombre,Fecha,Participantes");
            List<Actuacion> actuaciones = banda.getActuaciones();

            for (Actuacion actuacion : actuaciones) {
                writer.print(actuacion.getNombre() + "," + actuacion.getFecha());

                // Save participants
                List<Participante> participantes = actuacion.getParticipantes();
                for (Participante participante : participantes) {
                    writer.print("," + participante.getClass().getSimpleName() + ":" + participante.getNombre());
                }

                writer.println();
            }

            System.out.println("Datos guardados exitosamente en formato CSV.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos en formato CSV.");
        }
    }

    private void cargarDatosCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("banda.csv"))) {
            // Saltamos la primera línea que contiene los encabezados
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String nombre = parts[0];
                    LocalDateTime fecha = LocalDateTime.parse(parts[1]);

                    Actuacion nuevaActuacion = new Actuacion(nombre);
                    nuevaActuacion.setFecha(fecha);

                    // Load participants
                    for (int i = 2; i < parts.length; i++) {
                        String[] participantInfo = parts[i].split(":");
                        if (participantInfo.length == 2) {
                            String tipoParticipante = participantInfo[0];
                            String nombreParticipante = participantInfo[1];

                            switch (tipoParticipante) {
                                case "Director":
                                    nuevaActuacion.agregarParticipante(new Director(nombreParticipante));
                                    break;
                                case "Socio":
                                    nuevaActuacion.agregarParticipante(new Socio(nombreParticipante, 0)); // You may need to adjust this
                                    break;
                                case "Musico":
                                    nuevaActuacion.agregarParticipante(new Musico(nombreParticipante, 0)); // You may need to adjust this
                                    break;
                                // Add more cases for other types of participants if needed
                            }
                        }
                    }

                    banda.agregarActuacion(nuevaActuacion);
                }
            }

            System.out.println("Datos cargados exitosamente desde formato CSV.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos desde formato CSV.");
        }
    }
}