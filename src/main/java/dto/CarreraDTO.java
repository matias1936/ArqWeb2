package dto;

public class CarreraDTO {

    private int idCarrera;
    private String carrera;
    private int duracion;

    public CarreraDTO(int idCarrera, String carrera, int duracion) {
        this.idCarrera = idCarrera;
        this.carrera = carrera;
        this.duracion = duracion;
    }

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