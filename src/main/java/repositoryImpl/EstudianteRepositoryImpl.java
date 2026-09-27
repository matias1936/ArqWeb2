package repositoryImpl;

import dto.EstudianteDTO;
import entity.Estudiante;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import repository.EstudianteRepository;

import java.util.List;

/**
 * Implementacion JPA del contrato de estudiantes. EntityManager traduce las
 * operaciones de esta clase a SQL para la base configurada en persistence.xml.
 */
public class EstudianteRepositoryImpl implements EstudianteRepository {

    private final EntityManager em;

    public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(Estudiante estudiante) {
        // Una escritura JPA debe realizarse dentro de una transaccion.
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(estudiante); // Marca la entidad nueva para INSERT.
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback(); // Evita dejar una escritura parcial ante un error.
            }
            throw e;
        }
    }

    @Override
    public void darAltaEstudiante(Estudiante estudiante) {
        // El nombre expresa el caso de uso; la persistencia es la misma que insertar.
        insertar(estudiante);
    }

    @Override
    public List<EstudianteDTO> recuperarTodosOrdenados() {
        // La proyeccion "new dto.EstudianteDTO" devuelve datos de lectura, no entidades administradas.
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
        // :lu es un parametro JPQL; evita concatenar valores dentro de la consulta.
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
        // El criterio de orden pedido para esta consulta es el apellido ascendente.
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
        // find busca por clave primaria y devuelve null cuando no hay coincidencia.
        return em.find(Estudiante.class, dni);
    }
}
