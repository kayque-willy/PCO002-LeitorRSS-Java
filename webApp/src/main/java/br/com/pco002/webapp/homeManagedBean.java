package br.com.pco002.webapp;

import br.com.pco002.model.Inscricao;
import br.com.pco002.model.Notificacao;
import br.com.pco002.model.Topico;
import br.com.pco002.model.Usuario;
import br.com.pco002.service.TopicoService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean(name = "home")
@ViewScoped
public class homeManagedBean extends genericManagedBean {

    @Inject()
    private TopicoService topicoService;
    private List<Topico> topicos = new ArrayList();
    private List<Notificacao> notificacoes = new ArrayList();

    public homeManagedBean() {
        topicoService = new TopicoService();
        reloadUsuarioSessao();
        carregarInscricoes();
        carregarNotificaoes();
    }

    private void carregarInscricoes() {
        topicos = new ArrayList();
        for (Inscricao inscricao : getUsuarioSessao().getInscricoes()) {
            topicos.add(inscricao.getTopico());
        }
    }

    private void carregarNotificaoes() {
        notificacoes = new ArrayList();
        for (Inscricao inscricao : getUsuarioSessao().getInscricoes()) {
            notificacoes.addAll(topicoService.getNotificacao(inscricao));
        }
    }

    public void cancelarInscricao(Topico topico) {
        List<Inscricao> remove = new ArrayList();
        for (Inscricao inscricao : getUsuarioSessao().getInscricoes()) {
            if (inscricao.getTopico().equals(topico)) {
                //Remove a inscrição do tópico
                topico.getInscricao().remove(inscricao);
                remove.add(inscricao);
            }
        }
        //Remove a incrição do usuario
        getUsuarioSessao().getInscricoes().removeAll(remove);
        //Salva o tópico
        topicoService.save(topico);
        //Salva o usuário
        getUsuarioService().save(getUsuarioSessao());
        //Recarrega o usuario na sessão
        reloadUsuarioSessao();
        //Recarrega as inscrições
        carregarInscricoes();
    }

//    GETS E SETS
    public Usuario getUsuario() {
        return getUsuarioSessao();
    }

    public void setUsuario(Usuario usuario) {
        this.setUsuarioSessao(usuario);
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }
   
}
