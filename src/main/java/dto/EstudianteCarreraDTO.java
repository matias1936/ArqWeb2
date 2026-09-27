package dto;

/**
 * Vista de una matricula que usa IDs en lugar de transportar las entidades
 * Estudiante y Carrera completas.
 */
public class EstudianteCarreraDTO {

    private int id;
    private int dniEstudiante;
    private int idCarrera;
    private int inscripcion;
    private Integer graduacion;
    private int antiguedad;

    public EstudianteCarreraDTO(int id,
                                int dniEstudiante,
                                int idCarrera,
                                int inscripcion,
                                Integer graduacion,
                                int antiguedad) {

        // Los valores describen la relacion y sus datos propios: anios y antiguedad.
        this.id = id;
        this.dniEstudiante = dniEstudiante;
        this.idCarrera = idCarrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
    }

    // Getters y setters facilitan transportar la informacion sin involucrar a JPA.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDniEstudiante() {
        return dniEstudiante;
    }

    public void setDniEstudiante(int dniEstudiante) {
        this.dniEstudiante = dniEstudiante;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public int getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(int inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Integer getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(Integer graduacion) {
        this.graduacion = graduacion;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public String toString() {
        return "EstudianteCarreraDTO{" +
                "id=" + id +
                ", dniEstudiante=" + dniEstudiante +
                ", idCarrera=" + idCarrera +
                ", inscripcion=" + inscripcion +
                ", graduacion=" + graduacion +
                ", antiguedad=" + antiguedad +
                '}';
    }
}
