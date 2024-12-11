package gestion;

import daos.EjemplarDAO;
import daos.LibroDAO;
import daos.PrestamoDAO;
import daos.UsuarioDAO;
import jakarta.persistence.EntityManager;

public class GestionBiblio {
    private final LibroService libroService;
    private final EjemplarService ejemplarService;
    private final UsuarioService usuarioService;
    private final PrestamoService prestamoService;

    public GestionBiblio(EntityManager em) {
        LibroDAO libroDAO = new LibroDAO(em);
        EjemplarDAO ejemplarDAO = new EjemplarDAO(em);
        UsuarioDAO usuarioDAO = new UsuarioDAO(em);
        PrestamoDAO prestamoDAO = new PrestamoDAO(em);

        // Inicializar los servicios con los DAOs
        this.libroService = new LibroService(libroDAO);
        this.ejemplarService = new EjemplarService(ejemplarDAO);
        this.usuarioService = new UsuarioService(usuarioDAO);
        this.prestamoService = new PrestamoService(prestamoDAO);
    }

    public LibroService getLibroService() {
        return libroService;
    }

    public EjemplarService getEjemplarService() {
        return ejemplarService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public PrestamoService getPrestamoService() {
        return prestamoService;
    }
}

