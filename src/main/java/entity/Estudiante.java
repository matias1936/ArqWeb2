package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad JPA que representa la tabla estudiante del modelo. Se usa cuando se
 * guarda o relaciona un estudiante; las consultas de lectura devuelven DTOs.
 */
@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id // El DNI identifica de forma unica a cada estudiante.
    private int dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String ciudad;

    @Column(name = "LU", unique = true, nullable = false) // La libreta universitaria tampoco se repite.
    private int LU;

    // Lado inverso: una persona puede estar matriculada en varias carreras.
    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteCarrera> carreras = new ArrayList<>();


    // Constructor vacío requerido por JPA
    protected Estudiante() {
    }

    public Estudiante(int dni, String nombre, String apellido,
                      String genero, int edad, String ciudad, int LU) {
        // Constructor usado por servicios y carga CSV para crear la entidad completa.
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edad = edad;
        this.ciudad = ciudad;
        this.LU = LU;
    }


    // Los getters y setters permiten a JPA y al resto de las capas usar los campos.
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getLU() {
        return LU;
    }

    public void setLU(int LU) {
        this.LU = LU;
    }

    public List<EstudianteCarrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<EstudianteCarrera> carreras) {
        this.carreras = carreras;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", LU=" + LU +
                '}';
    }
}
