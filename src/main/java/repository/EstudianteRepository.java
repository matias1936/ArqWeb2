package repository;

import dto.EstudianteDTO;
import entity.Estudiante;

import java.util.List;

/**
 * Contrato de acceso a datos de estudiantes. Declara las operaciones de los
 * puntos 2.a, 2.c, 2.d y 2.e; la implementacion JPA esta en repositoryImpl.
 */
public interface EstudianteRepository {

    // Para cargar estudiantes.csv
    /** Persiste un estudiante leido durante la carga inicial. */
    void insertar(Estudiante estudiante);

    // Punto 2.a
    /** Punto 2.a: guarda el estudiante creado desde la aplicacion. */
    void darAltaEstudiante(Estudiante estudiante);

    // Punto 2.c
    /** Punto 2.c: devuelve estudiantes ordenados por un criterio simple. */
    List<EstudianteDTO> recuperarTodosOrdenados();

    // Punto 2.d
    /** Punto 2.d: busca el estudiante asociado a una libreta universitaria. */
    EstudianteDTO recuperarPorLU(int lu);

    // Punto 2.e
    /** Punto 2.e: filtra los estudiantes por genero. */
    List<EstudianteDTO> recuperarPorGenero(String genero);

    // Útil para el punto 2.b al momento de matricular
    /** Busca la entidad por DNI para validar altas y crear matriculas. */
    Estudiante buscarPorDni(int dni);
}
