package repositoryImpl;

import dto.CarreraInscriptosDTO;
import dto.ReporteCarreraDTO;
import entity.Carrera;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import repository.CarreraRepository;

import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository {

    private final EntityManager em;

    public CarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(Carrera carrera) {

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
        return em.find(Carrera.class, idCarrera);
    }

    @Override
    public List<CarreraInscriptosDTO> recuperarCarrerasConInscriptos() {

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
        // Lo hacemos después porque el punto 3
        // necesita agrupar por carrera y por año,
        // contando inscriptos y egresados.
        return null;
    }
}
