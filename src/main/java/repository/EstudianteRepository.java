package repository;

import dto.EstudianteDTO;
import entity.Estudiante;

import java.util.List;

public interface EstudianteRepository {

    // Para cargar estudiantes.csv
    void insertar(Estudiante estudiante);

    // Punto 2.a
    void darAltaEstudiante(Estudiante estudiante);

    // Punto 2.c
    List<EstudianteDTO> recuperarTodosOrdenados();

    // Punto 2.d
    EstudianteDTO recuperarPorLU(int lu);

    // Punto 2.e
    List<EstudianteDTO> recuperarPorGenero(String genero);

    // Útil para el punto 2.b al momento de matricular
    Estudiante buscarPorDni(int dni);
}
