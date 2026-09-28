package service;

import dto.CarreraInscriptosDTO;
import dto.ReporteCarreraDTO;
import java.util.List;
import entity.Carrera;
import repository.CarreraRepository;

/**
 * Coordina los casos de uso relacionados con carreras. No conoce JPA ni SQL:
 * se comunica mediante el contrato CarreraRepository.
 */
public class CarreraService {

    private final CarreraRepository repositorio;

    public CarreraService(CarreraRepository repositorio) {
        // Main entrega la dependencia para desacoplar esta logica de JPA.
        this.repositorio = repositorio;
    }

    /**
     * Lee carreras.csv y guarda solo las carreras cuyo ID todavia no existe.
     * 
     * @return cantidad de carreras incorporadas durante esta ejecucion.
     */
    public int cargarDesdeCSV() {
        // Un arreglo permite incrementar el contador dentro de la lambda del lector
        // CSV.
        int[] cargadas = { 0 };
        CsvReader.leer("carreras.csv", 3, (linea, valores) -> {
            // El orden de columnas es: id_carrera, carrera, duracion.
            int id = Integer.parseInt(valores[0]);
            if (repositorio.buscarPorId(id) == null) {
                repositorio.insertar(new Carrera(id, valores[1], Integer.parseInt(valores[2])));
                cargadas[0]++;
            }
        });
        return cargadas[0];
    }

    public List<CarreraInscriptosDTO> recuperarCarrerasConInscriptos() {
        return repositorio.recuperarCarrerasConInscriptos();
    }

    public List<ReporteCarreraDTO> generarReporteCarreras() {
        return repositorio.generarReporteCarreras();
    }
}
