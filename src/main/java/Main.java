import entity.Estudiante;
import repository.CarreraRepository;
import repository.EstudianteCarreraRepository;
import repository.EstudianteRepository;
import repositoryImpl.CarreraRepositoryImpl;
import repositoryImpl.EstudianteCarreraRepositoryImpl;
import repositoryImpl.EstudianteRepositoryImpl;
import service.CarreraService;
import service.EstudianteCarreraService;
import service.EstudianteService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 * Punto de entrada de la aplicacion. Arma las capas, carga los datos iniciales
 * y conversa con la persona que usa el menu. No contiene consultas JPQL ni
 * persistencia: esas responsabilidades se delegan a servicios y repositorios.
 */
public class Main {

    public static void main(String[] args) {
        // "arqweb2" debe coincidir con el nombre definido en persistence.xml.
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("arqweb2");
        try {
            EntityManager em = fabrica.createEntityManager();
            try (Scanner entrada = new Scanner(System.in)) {
                // Las interfaces definen que se puede hacer; las clases Impl usan JPA para hacerlo.
                CarreraRepository carreras = new CarreraRepositoryImpl(em);
                EstudianteRepository estudiantes = new EstudianteRepositoryImpl(em);
                EstudianteCarreraRepository matriculas = new EstudianteCarreraRepositoryImpl(em);

                // Los servicios reciben repositorios para aplicar las reglas del caso de uso.
                CarreraService carreraService = new CarreraService(carreras);
                EstudianteService estudianteService = new EstudianteService(estudiantes);
                EstudianteCarreraService matriculaService =
                        new EstudianteCarreraService(matriculas, estudiantes, carreras);

                // La carga es idempotente: al ejecutar nuevamente se omiten registros existentes.
                System.out.println("Carreras cargadas: " + carreraService.cargarDesdeCSV());
                System.out.println("Estudiantes cargados: " + estudianteService.cargarDesdeCSV());
                System.out.println("Matrículas cargadas: " + matriculaService.cargarDesdeCSV());

                mostrarMenu(entrada, estudianteService, matriculaService);
            } finally {
                em.close();
            }
        } finally {
            fabrica.close();
        }
    }

    private static void mostrarMenu(Scanner entrada, EstudianteService estudiantes,
                                    EstudianteCarreraService matriculas) {
        // El menu solo recopila datos; las validaciones de negocio viven en los servicios.
        while (true) {
            System.out.println("\n1. Dar de alta un estudiante");
            System.out.println("2. Matricular un estudiante en una carrera");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            if (!entrada.hasNextLine()) {
                return;
            }

            String opcion = entrada.nextLine().trim();
            try {
                switch (opcion) {
                    case "1" -> {
                        // Se construye la entidad con los datos solicitados y se delega el alta.
                        Estudiante estudiante = new Estudiante(
                                leerEntero(entrada, "DNI: "),
                                leerTexto(entrada, "Nombre: "),
                                leerTexto(entrada, "Apellido: "),
                                leerTexto(entrada, "Género: "),
                                leerEntero(entrada, "Edad: "),
                                leerTexto(entrada, "Ciudad: "),
                                leerEntero(entrada, "LU: ")
                        );
                        estudiantes.darAltaEstudiante(estudiante);
                        System.out.println("Estudiante dado de alta.");
                    }
                    case "2" -> {
                        // El servicio comprueba que existan el estudiante y la carrera solicitados.
                        int dni = leerEntero(entrada, "DNI del estudiante: ");
                        int carrera = leerEntero(entrada, "ID de la carrera: ");
                        int anio = leerEntero(entrada, "Año de inscripción: ");
                        matriculas.matricularEstudiante(dni, carrera, anio);
                        System.out.println("Estudiante matriculado.");
                    }
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static int leerEntero(Scanner entrada, String mensaje) {
        // Reutiliza leerTexto para evitar aceptar campos vacios antes de convertir a numero.
        return Integer.parseInt(leerTexto(entrada, mensaje));
    }

    private static String leerTexto(Scanner entrada, String mensaje) {
        System.out.print(mensaje);
        String valor = entrada.nextLine().trim();
        if (valor.isEmpty()) {
            throw new IllegalArgumentException("El valor no puede estar vacío.");
        }
        return valor;
    }
}
