package br.com.pco002.webapp;

import br.com.pco002.model.Inscricao;
import br.com.pco002.model.Topico;
import br.com.pco002.model.Usuario;
import br.com.pco002.service.TopicoService;
import br.com.pco002.service.UsuarioService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean(name = "inscricao")
@ViewScoped
public class inscricaoManagedBean extends genericManagedBean {

    @Inject()
    private TopicoService topicoService;
    private List<Topico> topicos = new ArrayList();
    private List<Topico> topicosAux = new ArrayList();

    public inscricaoManagedBean() {
        topicoService = new TopicoService();
        carregarTopicos();
        verificarInscricoes();
    }

    //Carrega todos os topicos disponiveis
    private void carregarTopicos() {
        topicos = topicoService.getAll();
    }

    //Remove os topicos ja inscritos
    private void verificarInscricoes() {
        for (Inscricao inscricao : getUsuarioSessao().getInscricoes()) {
            for (Topico topico : topicos) {
                if (Objects.equals(topico.getId(), inscricao.getTopico().getId())) {
                    topicosAux.add(topico);
                }
            }
        }
        topicos.removeAll(topicosAux);
    }
    
    public void inscrever(Topico topico){
        //Cria uma nova inscrição
        Inscricao inscricao = new Inscricao();
        inscricao.setData(new Date());
        //Vincula a inscrição ao topico
        inscricao.setTopico(topico);
        topico.getInscricao().add(inscricao);
        //Vincula a inscrição ao usuário
        inscricao.setUsuario(getUsuarioSessao());
        getUsuarioSessao().getInscricoes().add(inscricao);
        //Salva o topico
        topicoService.save(topico);
        reloadUsuarioSessao();
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

}
