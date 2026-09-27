package service;

import entity.Estudiante;
import repository.EstudianteRepository;

/**
 * Contiene las reglas previas al alta y a la carga masiva de estudiantes.
 */
public class EstudianteService {

    private final EstudianteRepository repositorio;

    public EstudianteService(EstudianteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public int cargarDesdeCSV() {
        // Se evita repetir estudiantes cuando el programa se inicia mas de una vez.
        int[] cargados = {0};
        CsvReader.leer("estudiantes.csv", 7, (linea, valores) -> {
            // El CSV usa: DNI, nombre, apellido, edad, genero, ciudad, LU.
            int dni = Integer.parseInt(valores[0]);
            if (repositorio.buscarPorDni(dni) == null) {
                repositorio.insertar(new Estudiante(
                        dni, valores[1], valores[2], valores[4],
                        Integer.parseInt(valores[3]), valores[5], Integer.parseInt(valores[6])
                ));
                cargados[0]++;
            }
        });
        return cargados[0];
    }

    public void darAltaEstudiante(Estudiante estudiante) {
        // El DNI es la clave primaria de Estudiante; se valida antes de persistir.
        if (repositorio.buscarPorDni(estudiante.getDni()) != null) {
            throw new IllegalArgumentException("Ya existe un estudiante con ese DNI");
        }
        repositorio.darAltaEstudiante(estudiante);
    }
}
