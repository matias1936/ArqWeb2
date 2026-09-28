package dto;

/**
 * Resultado del punto 2.f. JPQL crea este DTO directamente mediante
 * "new dto.CarreraInscriptosDTO(...)" y COUNT devuelve un Long.
 */
public class CarreraInscriptosDTO {

    private final int idCarrera;
    private final String carrera;
    private final Long cantidadInscriptos;

    public CarreraInscriptosDTO(int idCarrera, String carrera, Long cantidadInscriptos) {
        // El orden debe coincidir con SELECT: ID, nombre y COUNT de matriculas.
        this.idCarrera = idCarrera;
        this.carrera = carrera;
        this.cantidadInscriptos = cantidadInscriptos;
    }

    // Solo se exponen getters porque el resultado de una consulta no se modifica.
    public int getIdCarrera() {
        return idCarrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public Long getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    @Override
    public String toString() {
        return "CarreraInscriptosDTO{" +
                "idCarrera=" + idCarrera +
                ", carrera='" + carrera + '\'' +
                ", cantidadInscriptos=" + cantidadInscriptos +
                '}';
    }

}
