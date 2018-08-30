package br.com.pco002.webapp;

import br.com.pco002.model.Inscricao;
import br.com.pco002.model.Topico;
import br.com.pco002.service.TopicoService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean(name = "inscricao")
@SessionScoped
public class inscricaoManagedBean extends genericManagedBean{

    @Inject()
    private TopicoService topicoService;
    private List<Topico> topicos  = new ArrayList();
    
    public inscricaoManagedBean() {
        topicoService = new TopicoService();
        carregarTopicos();
        verificarInscricoes();
    }
    
    //Carrega todos os topicos disponiveis
    private void carregarTopicos(){
        topicos = topicoService.getAll();
    }
    
    //Remove os topicos ja inscritos
    private void verificarInscricoes(){
        List<Topico> topicoUsuario = new ArrayList();
        for (Inscricao inscricao : getUsuarioSessao().getInscricoes()) {
            Topico topico = inscricao.getTopico();
            topico.setInscricao(null);
            topicoUsuario.add(topico);
        }
        if (topicos.containsAll(topicoUsuario)) {
            topicos.removeAll(topicoUsuario);
        }
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

}
