package br.com.pco002.webapp;

import br.com.pco002.model.Usuario;
import br.com.pco002.util.SessionController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "home")
@SessionScoped
public class homeManagedBean {

    private SessionController sessionController;
    private Usuario usuario = new Usuario();
    
    public homeManagedBean() {
        this.sessionController = new SessionController();
        usuario = sessionController.getUsuario();
    }
    
//    GETS E SETS

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

}
