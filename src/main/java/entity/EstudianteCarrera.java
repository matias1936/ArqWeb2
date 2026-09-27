package entity;

import javax.persistence.*;

/**
 * Entidad intermedia de la tabla estudiante_carrera. Resuelve la relacion
 * muchos-a-muchos y guarda los datos propios de cada matricula.
 */
@Entity
@Table(name = "estudiante_carrera")
public class EstudianteCarrera {

    @Id // Identifica una matricula concreta.
    private int id;

    // Muchas matriculas pueden pertenecer a un mismo estudiante.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    // Muchas matriculas pueden pertenecer a una misma carrera.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_carrera", nullable = false)
    private Carrera carrera;

    @Column(nullable = false)
    private int inscripcion;

    // Integer permite null cuando aun no existe anio de graduacion.
    @Column
    private Integer graduacion;

    @Column(nullable = false)
    private int antiguedad;


    // Constructor vacío requerido por JPA
    protected EstudianteCarrera() {
    }

    public EstudianteCarrera(int id,
                             Estudiante estudiante,
                             Carrera carrera,
                             int inscripcion,
                             Integer graduacion,
                             int antiguedad) {

        // Se reciben las entidades relacionadas porque JPA debe persistir las claves foraneas.
        this.id = id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
    }

    // Getters y setters son usados por el resto de las capas y por JPA.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
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
        return "EstudianteCarrera{" +
                "id=" + id +
                ", inscripcion=" + inscripcion +
                ", graduacion=" + graduacion +
                ", antiguedad=" + antiguedad +
                '}';
    }
}
