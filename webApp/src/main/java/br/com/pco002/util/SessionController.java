package br.com.pco002.util;

import br.com.pco002.model.Usuario;
import javax.faces.context.FacesContext;

public class SessionController {

    public Usuario getUsuario() {
       return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }

    public void setUsuario(Usuario usuario) {
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
    }

}
