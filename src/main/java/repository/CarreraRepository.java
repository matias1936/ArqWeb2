package repository;

import entities.Carrera;
import dto.CarreraInscriptosDTO;
import dto.ReporteCarreraDTO;

import java.util.List;

public interface CarreraRepository {

    // Para carga inicial de datos
    void insertar(Carrera carrera);

    // Necesario para matricular
    Carrera buscarPorId(int idCarrera);

    // Ejercicio 2.f
    List<CarreraInscriptosDTO> recuperarCarrerasConInscriptos();

    // Ejercicio 3
    List<ReporteCarreraDTO> generarReporteCarreras();
}