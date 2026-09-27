package repositoryImpl;

import dto.EstudianteDTO;
import entity.Estudiante;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import repository.EstudianteRepository;

import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {

    private final EntityManager em;

    public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(Estudiante estudiante) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(estudiante);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void darAltaEstudiante(Estudiante estudiante) {
        insertar(estudiante);
    }

    @Override
    public List<EstudianteDTO> recuperarTodosOrdenados() {

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
                FROM Estudiante e
                ORDER BY e.apellido ASC
                """, EstudianteDTO.class)
                .getResultList();
    }

    @Override
    public EstudianteDTO recuperarPorLU(int lu) {

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
                FROM Estudiante e
                WHERE e.LU = :lu
                """, EstudianteDTO.class)
                .setParameter("lu", lu)
                .getSingleResult();
    }

    @Override
    public List<EstudianteDTO> recuperarPorGenero(String genero) {

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
                FROM Estudiante e
                WHERE e.genero = :genero
                ORDER BY e.apellido ASC
                """, EstudianteDTO.class)
                .setParameter("genero", genero)
                .getResultList();
    }

    @Override
    public Estudiante buscarPorDni(int dni) {
        return em.find(Estudiante.class, dni);
    }
}
