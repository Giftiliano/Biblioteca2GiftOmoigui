package daos;

import entity.Ejemplar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class EjemplarDAO {
    private EntityManager em;

    public EjemplarDAO(EntityManager em) {
        this.em = em;
    }

    public List<Ejemplar> getAllEjemplares() {
        return em.createQuery("SELECT e FROM Ejemplar e", Ejemplar.class).getResultList();
    }

    public Ejemplar getEjemplarById(int id) {
        return em.find(Ejemplar.class, id);
    }

    public void createEjemplar(Ejemplar ejemplar) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(ejemplar);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void updateEjemplar(Ejemplar ejemplar) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(ejemplar);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deleteEjemplar(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Ejemplar ejemplar = em.find(Ejemplar.class, id);
            if (ejemplar != null) {
                em.remove(ejemplar);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
