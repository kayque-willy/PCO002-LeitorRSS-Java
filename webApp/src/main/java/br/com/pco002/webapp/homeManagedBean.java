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
public class homeManagedBean extends genericManagedBean{

    private List<Topico> inscricoes  = new ArrayList();
    
    public homeManagedBean() {
        carregarInscricoes();
    }
    
    private void carregarInscricoes(){
        for (Inscricao inscricao : getUsuarioSessao().getInscricoes()) {
            inscricoes.add(inscricao.getTopico());
        }
    }
    
//    GETS E SETS
    public Usuario getUsuario() {
        return getUsuarioSessao();
    }

    public void setUsuario(Usuario usuario) {
        this.setUsuarioSessao(usuario);
    }

    public List<Topico> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Topico> inscricoes) {
        this.inscricoes = inscricoes;
    }

}
