package daos;

import entity.Usuario;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UsuarioDAO {
    private EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    public List<Usuario> getAllUsuarios() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public Usuario getUsuarioById(int id) {
        return em.find(Usuario.class, id);
    }

    public void createUsuario(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public void updateUsuario(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    public void deleteUsuario(int id) {
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) em.remove(usuario);
        em.getTransaction().commit();
    }
}
