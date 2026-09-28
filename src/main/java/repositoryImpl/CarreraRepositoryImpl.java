package repositoryImpl;

import dto.CarreraInscriptosDTO;
import dto.ReporteCarreraDTO;
import entity.Carrera;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import repository.CarreraRepository;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

/**
 * Implementacion JPA de las operaciones de carreras, incluida la consulta
 * agregada del punto 2.f y el reporte anual del punto 3.
 */
public class CarreraRepositoryImpl implements CarreraRepository {

    private final EntityManager em;

    public CarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(Carrera carrera) {
        // Cada persistencia se confirma como una transaccion independiente.
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(carrera);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public Carrera buscarPorId(int idCarrera) {
        // id_carrera es la clave primaria de Carrera.
        return em.find(Carrera.class, idCarrera);
    }

    @Override
    public List<CarreraInscriptosDTO> recuperarCarrerasConInscriptos() {
        // JOIN excluye carreras sin matriculas; COUNT(ec) calcula sus inscriptos.
        // La expresion new DTO evita devolver entidades y relaciones innecesarias.
        return em.createQuery("""
                SELECT new dto.CarreraInscriptosDTO(
                    c.id_carrera,
                    c.carrera,
                    COUNT(ec)
                )
                FROM Carrera c
                JOIN c.estudiantes ec
                GROUP BY c.id_carrera, c.carrera
                ORDER BY COUNT(ec) DESC
                """, CarreraInscriptosDTO.class)
                .getResultList();
    }

    @Override
    public List<ReporteCarreraDTO> generarReporteCarreras() {
        // La clave compuesta permite reunir, en una sola fila, ambos conteos de una
        // carrera y un anio.
        Map<ReporteKey, long[]> totalesPorCarreraYAnio = new TreeMap<>(
                Comparator.comparing(ReporteKey::carrera)
                        .thenComparingInt(ReporteKey::anio)
                        .thenComparingInt(ReporteKey::idCarrera));

        // Primera consulta JPQL: los anios se toman de la fecha de inscripcion.
        List<Object[]> inscriptos = em.createQuery("""
                SELECT c.id_carrera, c.carrera, ec.inscripcion, COUNT(ec)
                FROM Carrera c
                JOIN c.estudiantes ec
                GROUP BY c.id_carrera, c.carrera, ec.inscripcion
                """, Object[].class).getResultList();

        for (Object[] fila : inscriptos) {
            // Object[] contiene, por orden, ID, nombre, anio y cantidad seleccionados por
            // JPQL.
            ReporteKey clave = new ReporteKey(
                    ((Number) fila[0]).intValue(),
                    (String) fila[1],
                    ((Number) fila[2]).intValue());
            totalesPorCarreraYAnio
                    .computeIfAbsent(clave, ignorado -> new long[2])[0] = ((Number) fila[3]).longValue();
        }

        // Segunda consulta JPQL: 0 y null representan que no existe una graduacion
        // registrada.
        List<Object[]> egresados = em.createQuery("""
                SELECT c.id_carrera, c.carrera, ec.graduacion, COUNT(ec)
                FROM Carrera c
                LEFT JOIN c.estudiantes ec
                WHERE ec.graduacion IS NOT NULL AND ec.graduacion <> 0
                GROUP BY c.id_carrera, c.carrera, ec.graduacion
                """, Object[].class).getResultList();

        for (Object[] fila : egresados) {
            ReporteKey clave = new ReporteKey(
                    ((Number) fila[0]).intValue(),
                    (String) fila[1],
                    ((Number) fila[2]).intValue());
            totalesPorCarreraYAnio
                    .computeIfAbsent(clave, ignorado -> new long[2])[1] = ((Number) fila[3]).longValue();
        }

        // TreeMap ya ordeno por carrera y anio; se transforma cada acumulado en el DTO
        // de salida.
        return totalesPorCarreraYAnio.entrySet().stream()
                .map(entrada -> new ReporteCarreraDTO(
                        entrada.getKey().idCarrera(),
                        entrada.getKey().carrera(),
                        entrada.getKey().anio(),
                        entrada.getValue()[0],
                        entrada.getValue()[1]))
                .toList();
    }

    /**
     * Clave inmutable usada solo para combinar los resultados de ambas consultas.
     */
    private record ReporteKey(int idCarrera, String carrera, int anio) {
    }
}
