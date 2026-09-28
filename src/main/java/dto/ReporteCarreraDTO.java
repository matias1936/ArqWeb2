package dto;

/**
 * Fila del punto 3: una carrera en un anio con sus totales de inscriptos y
 * egresados. Long es el tipo que produce COUNT en JPQL.
 */
public class ReporteCarreraDTO {

    private final int idCarrera;
    private final String carrera;
    private final int anio;
    private final Long cantidadInscriptos;
    private final Long cantidadEgresados;

    public ReporteCarreraDTO(int idCarrera, String carrera, int anio,
            Long cantidadInscriptos, Long cantidadEgresados) {
        // El orden coincide con el armado final del reporte en CarreraRepositoryImpl.
        this.idCarrera = idCarrera;
        this.carrera = carrera;
        this.anio = anio;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadEgresados = cantidadEgresados;
    }

    // Solo hay getters: el reporte se construye completo antes de devolverse.
    public int getIdCarrera() {
        return idCarrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public int getAnio() {
        return anio;
    }

    public Long getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public Long getCantidadEgresados() {
        return cantidadEgresados;
    }

    @Override
    public String toString() {
        return "Carrera: " + carrera
                + " | Año: " + anio
                + " | Inscriptos: " + cantidadInscriptos
                + " | Egresados: " + cantidadEgresados;
    }
}
