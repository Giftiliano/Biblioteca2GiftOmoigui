package gestion;

import daos.EjemplarDAO;
import entity.Ejemplar;

import java.util.ArrayList;
import java.util.List;

public class EjemplarService {
    private final EjemplarDAO ejemplarDAO;
    private List<Ejemplar> listaEjemplares = new ArrayList<>();

    public EjemplarService(EjemplarDAO ejemplarDAO) {
        this.ejemplarDAO = ejemplarDAO;
        sincronizar();
    }

    private void sincronizar() {
        listaEjemplares = ejemplarDAO.getAllEjemplares();
    }

    public void insertarEjemplar(Ejemplar ejemplar) {

        ejemplarDAO.createEjemplar(ejemplar);
        sincronizar();
    }

    public void actualizarEjemplar(Ejemplar ejemplar) {
        ejemplarDAO.updateEjemplar(ejemplar);
        sincronizar();
    }

    public Ejemplar buscarPorId(int id) {
        return ejemplarDAO.getEjemplarById(id);
    }

    public void eliminarEjemplar(int id) {
        ejemplarDAO.deleteEjemplar(id);
        sincronizar();
    }

    public List<Ejemplar> getListaEjemplares() {
        return listaEjemplares;
    }
}
