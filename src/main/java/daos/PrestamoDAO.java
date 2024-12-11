package daos;

import entity.Prestamo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;

public class PrestamoDAO {
    private EntityManager em;

    public PrestamoDAO(EntityManager em) {
        this.em = em;
    }

    public List<Prestamo> getAllPrestamos() {
        return em.createQuery("SELECT p FROM Prestamo p", Prestamo.class).getResultList();
    }

    public Prestamo getPrestamoById(int id) {
        return em.find(Prestamo.class, id);
    }

    public void createPrestamo(Prestamo prestamo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void updatePrestamo(Prestamo prestamo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(prestamo); // Actualiza el préstamo existente
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deletePrestamo(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Prestamo prestamo = em.find(Prestamo.class, id);
            if (prestamo != null) {
                em.remove(prestamo); // Elimina el préstamo
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void registrarDevolucion(int prestamoId) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Prestamo prestamo = em.find(Prestamo.class, prestamoId);
            if (prestamo != null) {
                prestamo.getEjemplar().setEstado("Disponible");
                em.merge(prestamo.getEjemplar());
                em.remove(prestamo);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
    public List<Prestamo> getPrestamosVencidosPorUsuarioDni(String dni) {
        String query = "SELECT p FROM Prestamo p WHERE p.usuario.dni = :dni AND p.fechaDevolucion < CURRENT_DATE";
        return em.createQuery(query, Prestamo.class)
                .setParameter("dni", dni)
                .getResultList();
    }

}