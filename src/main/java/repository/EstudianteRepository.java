package ...;

import dto.EstudianteDTO;
import entities.Estudiante;

public interface EstudianteRepository {
    void insertDesdeCSV(String rutaArchivo);
    List<Estudiante> buscarTodos(); //Criterio de ordenamiento necesario 2-c
    List<Estudiante> buscarPorUL(int numLibreta); // Punto 2-d
    List<Estudiante> buscarPorGenero(); //Punto 2-e
    //Bajo que criterio se usa la Clase o el DTO?
}