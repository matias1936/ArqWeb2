package service;

import entity.Carrera;
import entity.Estudiante;
import entity.EstudianteCarrera;
import repository.CarreraRepository;
import repository.EstudianteCarreraRepository;
import repository.EstudianteRepository;

public class EstudianteCarreraService {

    private final EstudianteCarreraRepository matriculas;
    private final EstudianteRepository estudiantes;
    private final CarreraRepository carreras;

    public EstudianteCarreraService(EstudianteCarreraRepository matriculas,
                                   EstudianteRepository estudiantes,
                                   CarreraRepository carreras) {
        this.matriculas = matriculas;
        this.estudiantes = estudiantes;
        this.carreras = carreras;
    }

    public int cargarDesdeCSV() {
        int[] cargadas = {0};
        CsvReader.leer("estudianteCarrera.csv", 6, (linea, valores) -> {
            int id = Integer.parseInt(valores[0]);
            if (matriculas.buscarPorId(id) != null) {
                return;
            }

            Estudiante estudiante = estudiantes.buscarPorDni(Integer.parseInt(valores[1]));
            Carrera carrera = carreras.buscarPorId(Integer.parseInt(valores[2]));
            if (estudiante == null || carrera == null) {
                System.err.println("Se omite estudianteCarrera.csv, línea " + linea
                        + ": estudiante o carrera inexistente");
                return;
            }

            matriculas.insertar(new EstudianteCarrera(
                    id, estudiante, carrera,
                    Integer.parseInt(valores[3]), Integer.parseInt(valores[4]),
                    Integer.parseInt(valores[5])
            ));
            cargadas[0]++;
        });
        return cargadas[0];
    }

    public void matricularEstudiante(int dni, int idCarrera, int anioInscripcion) {
        Estudiante estudiante = estudiantes.buscarPorDni(dni);
        if (estudiante == null) {
            throw new IllegalArgumentException("No existe un estudiante con ese DNI");
        }
        Carrera carrera = carreras.buscarPorId(idCarrera);
        if (carrera == null) {
            throw new IllegalArgumentException("No existe una carrera con ese ID");
        }
        EstudianteCarrera matricula = new EstudianteCarrera(
                matriculas.siguienteId(), estudiante, carrera, anioInscripcion, null, 0
        );
        matriculas.matricularEstudiante(matricula);
    }
}
