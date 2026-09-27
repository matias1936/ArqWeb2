package repositoryImpl;

import dto.EstudianteDTO;
import entities.EstudianteCarrera;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import repository.EstudianteCarreraRepository;

import java.util.List;

public class EstudianteCarreraRepositoryImpl
        implements EstudianteCarreraRepository {

    private final EntityManager em;

    public EstudianteCarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(EstudianteCarrera estudianteCarrera) {

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(estudianteCarrera);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void matricularEstudiante(EstudianteCarrera estudianteCarrera) {
        insertar(estudianteCarrera);
    }

    @Override
    public List<EstudianteDTO> recuperarEstudiantesPorCarreraYCiudad(int idCarrera, String ciudad) {

        return em.createQuery("""
                SELECT new dto.EstudianteDTO(
                    e.dni,
                    e.nombre,
                    e.apellido,
                    e.edad,
                    e.genero,
                    e.ciudad,
                    e.LU
                )
                FROM EstudianteCarrera ec
                JOIN ec.estudiante e
                WHERE ec.carrera.id_carrera = :idCarrera
                AND e.ciudad = :ciudad
                ORDER BY e.apellido ASC
                """, EstudianteDTO.class)
                .setParameter("idCarrera", idCarrera)
                .setParameter("ciudad", ciudad)
                .getResultList();
    }
}
