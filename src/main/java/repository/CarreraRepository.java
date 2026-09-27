package repository;

import entity.Carrera;
import dto.CarreraInscriptosDTO;
import dto.ReporteCarreraDTO;

import java.util.List;

/**
 * Contrato de persistencia y consultas de carreras. Incluye el punto 2.f y
 * el reporte anual solicitado en el punto 3.
 */
public interface CarreraRepository {

    // Para carga inicial de datos
    /** Persiste una carrera proveniente del CSV inicial. */
    void insertar(Carrera carrera);

    // Necesario para matricular
    /** Busca una carrera para comprobar que puede recibir una matricula. */
    Carrera buscarPorId(int idCarrera);

    // Ejercicio 2.f
    /** Punto 2.f: devuelve cada carrera junto con su cantidad de inscriptos. */
    List<CarreraInscriptosDTO> recuperarCarrerasConInscriptos();

    // Ejercicio 3
    /** Punto 3: genera el reporte de inscriptos y egresados por carrera y anio. */
    List<ReporteCarreraDTO> generarReporteCarreras();
}
