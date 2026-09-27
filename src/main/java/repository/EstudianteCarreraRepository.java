package repository;

import dto.EstudianteDTO;
import entity.EstudianteCarrera;

import java.util.List;

/**
 * Contrato de la inscripcion de un estudiante en una carrera. Cubre los
 * puntos 2.b y 2.g de la consigna.
 */
public interface EstudianteCarreraRepository {

    // Para cargar estudianteCarrera.csv
    /** Persiste una matricula leida desde el CSV inicial. */
    void insertar(EstudianteCarrera estudianteCarrera);

    /** Permite saber si ya se cargo una matricula con ese ID. */
    EstudianteCarrera buscarPorId(int id);

    /** Calcula el proximo ID que puede asignarse a una matricula nueva. */
    int siguienteId();

    // Punto 2.b
    /** Punto 2.b: guarda una matricula ya validada por el servicio. */
    void matricularEstudiante(EstudianteCarrera estudianteCarrera);

    // Punto 2.g
    /** Punto 2.g: filtra estudiantes por carrera y ciudad de residencia. */
    List<EstudianteDTO> recuperarEstudiantesPorCarreraYCiudad(
            int idCarrera,
            String ciudad
    );
}
