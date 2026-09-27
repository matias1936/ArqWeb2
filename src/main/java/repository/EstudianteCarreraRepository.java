package repository;

import dto.EstudianteDTO;
import entities.EstudianteCarrera;

import java.util.List;

public interface EstudianteCarreraRepository {

    // Para cargar estudianteCarrera.csv
    void insertar(EstudianteCarrera estudianteCarrera);

    // Punto 2.b
    void matricularEstudiante(EstudianteCarrera estudianteCarrera);

    // Punto 2.g
    List<EstudianteDTO> recuperarEstudiantesPorCarreraYCiudad(
            int idCarrera,
            String ciudad
    );
}