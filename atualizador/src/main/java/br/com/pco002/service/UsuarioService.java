package br.com.pco002.service;

import br.com.pco002.model.Usuario;
import br.com.pco002.repository.UsuarioRepository;
import java.util.List;

public class UsuarioService {

    private static final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void remove(Usuario usuario) {
        usuarioRepository.remove(Usuario.class, usuario.getId());
    }

    public Usuario findById(Long id) {
        return (Usuario) usuarioRepository.findById(Usuario.class, id);
    }

    public List<Usuario> getAll() {
        return usuarioRepository.getAll();
    }
}
