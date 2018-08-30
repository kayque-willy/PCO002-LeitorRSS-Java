package br.com.pco002.webapp;

import br.com.pco002.model.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean(name = "index")
@ViewScoped
public class indexManagedBean extends genericManagedBean{

    private Usuario usuario = new Usuario();

    public indexManagedBean() {}

    public String fazerLogin() {
        usuario = getUsuarioService().fazerLogin(usuario.getEmail(), usuario.getSenha());
        if (usuario != null) {
            setUsuarioSessao(usuario);
            return "home/home";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ou senha incorretos!", "Erro no Login!"));
            return null;
        }
    }

    //TESTE DE SQL PELO QUERY DO ENTITY MANAGER
    public String testeSql() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("rss-persistence").createEntityManager();
        Query queryObj = entityManager.createQuery("SELECT u FROM Usuario u");
        Usuario usuario = (Usuario) queryObj.getSingleResult();
        if (usuario != null) {
            return null;
        } else {
            return null;
        }
    }

    //GETS E SETS
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
