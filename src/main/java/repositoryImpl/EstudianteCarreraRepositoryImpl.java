package repositoryImpl;

import dto.EstudianteDTO;
import entity.EstudianteCarrera;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import repository.EstudianteCarreraRepository;

import java.util.List;

/**
 * Implementacion JPA de matriculas y de la consulta del punto 2.g.
 */
public class EstudianteCarreraRepositoryImpl implements EstudianteCarreraRepository {

    private final EntityManager em;

    public EstudianteCarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(EstudianteCarrera estudianteCarrera) {
        // Al persistir la matricula, JPA guarda las referencias como claves foraneas.
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
    public EstudianteCarrera buscarPorId(int id) {
        return em.find(EstudianteCarrera.class, id);
    }

    @Override
    public boolean existeMatricula(int dni, int idCarrera) {
        Long cantidad = em.createQuery("""
                SELECT COUNT(ec)
                FROM EstudianteCarrera ec
                WHERE ec.estudiante.dni = :dni
                AND ec.carrera.id_carrera = :idCarrera
                """, Long.class)
                .setParameter("dni", dni)
                .setParameter("idCarrera", idCarrera)
                .getSingleResult();

        return cantidad > 0;
    }

    @Override
    public int siguienteId() {
        // COALESCE cambia el resultado vacio por 0 para que la primera matricula reciba
        // ID 1.
        Number maximo = em.createQuery(
                "SELECT COALESCE(MAX(ec.id), 0) FROM EstudianteCarrera ec", Number.class).getSingleResult();
        return maximo.intValue() + 1;
    }

    @Override
    public void matricularEstudiante(EstudianteCarrera estudianteCarrera) {
        // El servicio ya valido las referencias; este metodo solo persiste.
        insertar(estudianteCarrera);
    }

    @Override
    public List<EstudianteDTO> recuperarEstudiantesPorCarreraYCiudad(int idCarrera, String ciudad) {
        // Se parte de la matricula para unir carrera y estudiante en una sola consulta
        // JPQL.
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
