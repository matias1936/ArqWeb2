package dto;

/**
 * Vista transportable de los datos basicos de una carrera. A diferencia de la
 * entidad, no contiene la coleccion de matriculas ni anotaciones JPA.
 */
public class CarreraDTO {

    private int idCarrera;
    private String carrera;
    private int duracion;

    public CarreraDTO(int idCarrera, String carrera, int duracion) {
        // Constructor para crear una representacion de datos fuera de la capa de persistencia.
        this.idCarrera = idCarrera;
        this.carrera = carrera;
        this.duracion = duracion;
    }

    // Getters y setters permiten usar el DTO en interfaces o futuras APIs.
    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
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

    @Override
    public String toString() {
        return "CarreraDTO{" +
                "idCarrera=" + idCarrera +
                ", carrera='" + carrera + '\'' +
                ", duracion=" + duracion +
                '}';
    }
}
