package br.com.pco002.webapp;

import br.com.pco002.model.Inscricao;
import br.com.pco002.model.Topico;
import br.com.pco002.model.Usuario;
import br.com.pco002.util.SessionController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "home")
@SessionScoped
public class homeManagedBean {

    private SessionController sessionController;
    private Usuario usuario = new Usuario();
    private List<Topico> inscricoes  = new ArrayList();
    
    public homeManagedBean() {
        this.sessionController = new SessionController();
        usuario = sessionController.getUsuario();
        carregarInscricoes();
    }
    
    private void carregarInscricoes(){
        for (Inscricao inscricao : usuario.getInscricoes()) {
            inscricoes.add(inscricao.getTopico());
        }
    }
    
//    GETS E SETS
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Topico> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Topico> inscricoes) {
        this.inscricoes = inscricoes;
    }

}
