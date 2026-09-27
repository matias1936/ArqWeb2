package dto;

/**
 * Vista de lectura de un estudiante. Los repositorios JPQL lo construyen para
 * devolver datos sin exponer la entidad JPA ni sus relaciones.
 */
public class EstudianteDTO {

    private int dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;
    private int lu;

    public EstudianteDTO(int dni, String nombre, String apellido,
                         int edad, String genero,
                         String ciudad, int lu) {
        // El orden de parametros coincide con las expresiones "new EstudianteDTO" de JPQL.
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.lu = lu;
    }

    // Solo hay getters porque estos DTO se usan como resultados de consulta.
    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getLu() {
        return lu;
    }
}
