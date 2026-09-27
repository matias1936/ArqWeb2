package service;

import entity.Carrera;
import repository.CarreraRepository;

public class CarreraService {

    private final CarreraRepository repositorio;

    public CarreraService(CarreraRepository repositorio) {
        this.repositorio = repositorio;
    }

    public int cargarDesdeCSV() {
        int[] cargadas = {0};
        CsvReader.leer("carreras.csv", 3, (linea, valores) -> {
            int id = Integer.parseInt(valores[0]);
            if (repositorio.buscarPorId(id) == null) {
                repositorio.insertar(new Carrera(id, valores[1], Integer.parseInt(valores[2])));
                cargadas[0]++;
            }
        });
        return cargadas[0];
    }
}
