package gestion;

import daos.PrestamoDAO;
import entity.Prestamo;
import entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class PrestamoService {
    private final PrestamoDAO prestamoDAO;
    private List<Prestamo> listaPrestamos = new ArrayList<>();

    public PrestamoService(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
        sincronizar();
    }

    private void sincronizar() {
        listaPrestamos = prestamoDAO.getAllPrestamos();
    }

    public void crearPrestamo(Prestamo prestamo) {
        // Verificar que el ejemplar esté disponible
        if (!prestamo.getEjemplar().getEstado().equals("Disponible")) {
            throw new IllegalArgumentException("El ejemplar no está disponible para préstamo.");
        }


        // Verificar que el usuario no tenga penalización activa
        if (tienePenalizacionActiva(prestamo.getUsuario())) {
            throw new IllegalArgumentException("El usuario tiene una penalización activa.");
        }

        prestamoDAO.createPrestamo(prestamo);
        sincronizar();
    }


    public void actualizarPrestamo(Prestamo prestamo) {
        prestamoDAO.updatePrestamo(prestamo);
        sincronizar();
    }

    public Prestamo buscarPorId(int id) {
        return prestamoDAO.getPrestamoById(id);
    }

    public void eliminarPrestamo(int id) {
        prestamoDAO.deletePrestamo(id);
        sincronizar();
    }

    public List<Prestamo> getListaPrestamos() {
        return listaPrestamos;
    }

    public boolean tienePenalizacionActiva(Usuario usuario) {
        // Obtén los préstamos vencidos del usuario
        List<Prestamo> prestamosVencidos = prestamoDAO.getPrestamosVencidosPorUsuarioDni(usuario.getDni());

        // Cada préstamo vencido da 15 días de penalización
        int penalizacionTotal = prestamosVencidos.size() * 15;

        // Si hay algún préstamo vencido, el usuario tiene una penalización activa
        return penalizacionTotal > 0;
    }


}
