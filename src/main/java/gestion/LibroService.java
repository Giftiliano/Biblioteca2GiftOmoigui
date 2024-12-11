package gestion;

import daos.LibroDAO;
import entity.Libro;

import java.util.ArrayList;
import java.util.List;

public class LibroService {
    private final LibroDAO libroDAO;
    private List<Libro> listaLibros = new ArrayList<>();

    public LibroService(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
        sincronizar();
    }

    private void sincronizar() {
        listaLibros = libroDAO.getAllLibros();
    }

    public void insertarLibro(Libro libro) {
        libroDAO.createLibro(libro);
        sincronizar();
    }

    public void actualizarLibro(Libro libro) {
        libroDAO.updateLibro(libro);
        sincronizar();
    }

    public Libro buscarPorISBN(String isbn) {
        return libroDAO.getLibroByIsbn(isbn);
    }

    public void eliminarLibro(String isbn) {
        libroDAO.deleteLibro(isbn);
        sincronizar();
    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }
}
