package br.com.pco002.webapp;

import br.com.pco002.model.Usuario;
import br.com.pco002.service.UsuarioService;
import br.com.pco002.util.ResourceUtil;
import br.com.pco002.util.SessionController;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean(name = "index")
@SessionScoped
public class indexManagedBean {

    @Inject()
    private UsuarioService usuarioService;
    private SessionController sessionController;
    private Usuario usuario = new Usuario();

    public indexManagedBean() {
        this.usuarioService = new UsuarioService();
        this.sessionController = new SessionController();
    }

    public String fazerLogin() {
        usuario.setEmail(usuario.getEmail().toLowerCase().trim());
        usuario.setSenha(ResourceUtil.convertStringToMd5(usuario.getSenha()));
        usuario = usuarioService.fazerLogin(usuario.getEmail(), usuario.getSenha());
        if (usuario != null) {
             sessionController.setUsuario(usuario);
            return "main/home";
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
