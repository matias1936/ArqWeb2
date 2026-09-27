package service;

import entity.Estudiante;
import repository.EstudianteRepository;

public class EstudianteService {

    private final EstudianteRepository repositorio;

    public EstudianteService(EstudianteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public int cargarDesdeCSV() {
        int[] cargados = {0};
        CsvReader.leer("estudiantes.csv", 7, (linea, valores) -> {
            int dni = Integer.parseInt(valores[0]);
            if (repositorio.buscarPorDni(dni) == null) {
                repositorio.insertar(new Estudiante(
                        dni, valores[1], valores[2], valores[4],
                        Integer.parseInt(valores[3]), valores[5], Integer.parseInt(valores[6])
                ));
                cargados[0]++;
            }
        });
        return cargados[0];
    }

    public void darAltaEstudiante(Estudiante estudiante) {
        if (repositorio.buscarPorDni(estudiante.getDni()) != null) {
            throw new IllegalArgumentException("Ya existe un estudiante con ese DNI");
        }
        repositorio.darAltaEstudiante(estudiante);
    }
}
