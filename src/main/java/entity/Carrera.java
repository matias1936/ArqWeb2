package entity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Entidad JPA de la tabla carrera. Es el lado "una carrera" de la relacion
 * con las matriculas de estudiantes.
 */
@Entity
@Table(name="carrera")
public class Carrera {
    @Id private int id_carrera; // Clave primaria usada tambien como ID de carrera en el CSV.

    @Column(nullable=false) private String carrera;

    @Column(nullable=false) private int duracion;

    // Una carrera puede aparecer en muchas matriculas; la FK se encuentra en EstudianteCarrera.
    @OneToMany(mappedBy = "carrera") private List<EstudianteCarrera> estudiantes = new ArrayList<>();

    public Carrera (int id, String nombreCarrera, int duracion) {
        // Constructor para crear carreras desde el CSV o desde otra capa de entrada.
        this.id_carrera = id;
        this.carrera = nombreCarrera;
        this.duracion = duracion;
    }

    protected Carrera( ) {}//Constructor vacío requerido por JPA para instanciar

    // Getters y setters exponen los atributos mapeados sin hacer publica su representacion interna.
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
