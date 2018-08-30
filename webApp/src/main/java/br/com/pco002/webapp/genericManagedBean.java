package br.com.pco002.webapp;

import br.com.pco002.model.Usuario;
import br.com.pco002.service.UsuarioService;
import br.com.pco002.util.SessionController;
import javax.inject.Inject;

public class genericManagedBean {
    
     private SessionController sessionController;
     private Usuario usuarioSessao = new Usuario();
     @Inject() 
     private UsuarioService usuarioService;
     
    public genericManagedBean() {
        this.sessionController = new SessionController();
        this.usuarioService = new UsuarioService();
        this.usuarioSessao = sessionController.getUsuario();
    }
    
    public void reloadUsuarioSessao(){
        sessionController.removeUsuario();
        sessionController.setUsuario(usuarioService.findById(usuarioSessao.getId()));
    }

    public Usuario getUsuarioSessao() {
        return this.usuarioSessao;
    }

    public void setUsuarioSessao(Usuario usuario) {
        sessionController.setUsuario(usuario);
        this.usuarioSessao = usuario;
    }

    public SessionController getSessionController() {
        return this.sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public UsuarioService getUsuarioService() {
        return this.usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
}
