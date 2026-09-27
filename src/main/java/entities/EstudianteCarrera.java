package entities;

import jakarta.persistence.*;

@Entity
Table(name="estudiante_carrera")
public class EstudianteCarrera{
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante; //Acá se crea la FK real

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column(nullable=false)
    private int inscripcion;

    @Column(nullable=true)
    private Integer graduacion;

    @Column(nullable=false)
    private int antiguedad;

    protected EstudianteCarrera(){} //Constructor vacío requerido por JPA para instanciar

    public EstudianteCarrera(int id, Estudiante estudiante, Carrera carrera, int inscripcion, Integer graduacion, int antiguedad) {
        this.id = id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
    }

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

    @java.lang.Override
    public java.lang.String toString() {
        return "EstudianteCarrera{" +
                "id=" + id +
                ", estudiante=" + estudiante +
                ", carrera=" + carrera +
                ", inscripcion=" + inscripcion +
                ", graduacion=" + graduacion +
                ", antiguedad=" + antiguedad +
                '}';
    }
}