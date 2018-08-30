package br.com.pco002.webapp;

import br.com.pco002.model.Usuario;
import br.com.pco002.util.SessionController;

public class genericManagedBean {
    
     private SessionController sessionController;
     private Usuario usuarioSessao = new Usuario();

    public genericManagedBean() {
        this.sessionController = new SessionController();
        usuarioSessao = sessionController.getUsuario();
    }

    public Usuario getUsuarioSessao() {
        return usuarioSessao;
    }

    public void setUsuarioSessao(Usuario usuario) {
        sessionController.setUsuario(usuario);
        this.usuarioSessao = usuario;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }
    
}
