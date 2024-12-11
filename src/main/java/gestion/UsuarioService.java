package gestion;

import daos.UsuarioDAO;
import entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        sincronizar();
    }

    private void sincronizar() {
        listaUsuarios = usuarioDAO.getAllUsuarios();
    }

    public void insertarUsuario(Usuario usuario) {
        usuarioDAO.createUsuario(usuario);
        sincronizar();
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.updateUsuario(usuario);
        sincronizar();
    }

    public Usuario buscarPorDNI(int id) {
        return usuarioDAO.getUsuarioById(id);
    }

    public void eliminarUsuario(int id) {
        usuarioDAO.deleteUsuario(id);
        sincronizar();
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}
