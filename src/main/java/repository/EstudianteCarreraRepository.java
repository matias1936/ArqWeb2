package repository;

import ...

public interface EstudianteCarreraRepository {
    List<EstudiantesCarrerasDTO> carreras_de_estudiante(int dni_estudiante);
    List<CarrerasDTO> buscarCarrerasConInscriptos ();//Punto 2-f? Con ordenamiento por cantidad de inscriptos
    List<CarrerasDTO> buscarEstudiantesPorCarreraYCiudad (String carrera, String ciudad); //Punto 2-g?
}
