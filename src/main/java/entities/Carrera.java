package entities;

import jakarta.persistence.*;

@Entity
@Table(name="carrera")
public class Carrera {
    @Id
    private int id_carrera;

    @Column(nullable=false)
    private String carrera;

    @Column(nullable=false)
    private int duracion;

    @OneToMany(mappedBy = "carrera")
    private List<EstudianteCarrera> estudiantes = new ArrayList<>();

    public Carrera (int id, String nombreCarrera, int duracion) {
        this.id_carrera = id;
        this.carrera = nombreCarrera;
        this.duracion = duracion;
    }

    protected Carrera( {}) //Constructor vacío requerido por JPA para instanciar

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Carreras{" +
                "id_carrera=" + id_carrera +
                ", carrera='" + carrera + '\'' +
                ", duracion=" + duracion +
                '}';
    }
}