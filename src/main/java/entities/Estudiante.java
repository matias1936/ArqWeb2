package entities;

import jakarta.persistence.*;

@Entity
@Table(name="estudiante")
public class Estudiante {
    @Id
    private Long dni;

    @Collumn(nullable=false)
    private String nombre;

    @Collumn(nullable=false)
    private String apellido;

    @Column(nullable=false)
    private int edad;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private String genero;

    @Column
    private String ciudad;

    @Column(unique=true)
    private int LU;

    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteCarrera> carreras = new ArrayList<>();

    protected Estudiante(){} //Constructor vacío requerido por JPA para instanciar

    public Estudiante(int dni, String nombre, String apellido, String genero, int edad, String ciudad, int LU) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edad = edad;
        this.ciudad = ciudad;
        this.LU = LU;
    }

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

    @java.lang.Override
    public java.lang.String toString() {
        return "Estudiantes{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", LU=" + LU +
                ", carreras=" + carreras +
                '}';
    }
}



