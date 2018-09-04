package br.com.pco002.webapp;

import br.com.pco002.model.Inscricao;
import br.com.pco002.model.Topico;
import br.com.pco002.model.Url_topico;
import br.com.pco002.model.Usuario;
import br.com.pco002.service.TopicoService;
import br.com.pco002.util.FaceUtil;
import br.com.pco002.util.ResourceUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean(name = "index")
@ViewScoped
public class indexManagedBean extends genericManagedBean {

    @Inject()
    private TopicoService topicoService;
    private Usuario usuario = new Usuario();

    public indexManagedBean() {
        topicoService = new TopicoService();
    }

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

    //POPULA O BANCO COM OS TOPICOS E USUARIO INICIAL
    public void popularBanco() {
        //CRIA O TOPICO
        Topico topico = new Topico();
        topico.setNome("G1 - Noticias Gerais");
        topico.setUltimaAtualizacao(new Date());

        //CRIA LISTA DE URLS
        List<Url_topico> urls = new ArrayList<Url_topico>();

        //CRIA UMA URL
        Url_topico url = new Url_topico();
        url.setUrl("http://pox.globo.com/rss/g1/");
        urls.add(url);
        url.setTopico(topico);

        //CRIA A SEGUNDA URL
        url = new Url_topico();
        url.setUrl("http://pox.globo.com/rss/g1/economia/");
        urls.add(url);
        url.setTopico(topico);
        
        //CRIA A TERCEIRA URL
        url = new Url_topico();
        url.setUrl("http://pox.globo.com/rss/g1/educacao/");
        urls.add(url);
        url.setTopico(topico);
        
        //CRIA A QUARTA URL
        url = new Url_topico();
        url.setUrl("http://pox.globo.com/rss/g1/ciencia-e-saude/");
        urls.add(url);
        url.setTopico(topico);

        //SALVA A LISTA DE URLS NO TOPICO
        topico.setUrls(urls);
        
        //CRIA O SEGUNDO TOPICO
        Topico topico2 = new Topico();
        topico2.setNome("G1 - Noticias Brasil e Mundo");
        topico2.setUltimaAtualizacao(new Date());

        //CRIA LISTA DE URLS
        List<Url_topico> urls2 = new ArrayList<Url_topico>();

        //CRIA UMA URL
        Url_topico url2 = new Url_topico();
        url2.setUrl("http://pox.globo.com/rss/g1/brasil/");
        urls2.add(url2);
        url2.setTopico(topico2);

        //CRIA A SEGUNDA URL
        url2 = new Url_topico();
        url2.setUrl("http://pox.globo.com/rss/g1/mundo/");
        urls2.add(url2);
        url2.setTopico(topico2);
        
        //SALVA A LISTA DE URLS NO TOPICO
        topico2.setUrls(urls2);

        //CRIA A LISTA DE INSCRIÇÕES
        List<Inscricao> inscricoes = new ArrayList<Inscricao>();
        List<Inscricao> inscricoes2 = new ArrayList<Inscricao>();

        //CRIA O PRIMEIRO USUARIO
        Usuario usuario = new Usuario();
        usuario.setNome("Kayque Oliveira");
        usuario.setEmail("kayque-willy@hotmail.com");
        usuario.setSenha(ResourceUtil.convertStringToMd5("123"));
        usuario.setTipo(FaceUtil.Admin);

        //CRIA A PRIMEIRA INSCRIÇÃO PARA O USUARIO
        Inscricao inscricao = new Inscricao();
        inscricao.setTopico(topico);
        inscricao.setUsuario(usuario);
        
        //CRIA A SEGUNDA INSCRIÇÃO PARA O USUARIO
        Inscricao inscricao2 = new Inscricao();
        inscricao2.setTopico(topico2);
        inscricao2.setUsuario(usuario);
        
        inscricoes.add(inscricao);
        inscricoes2.add(inscricao2);
       
        //SALVA A LISTA DE INSCRIÇÕES NO TOPICO
        topico.setInscricao(inscricoes);
        topico2.setInscricao(inscricoes2);

        //SALVA OS TOPICOS NO BANCO
        topicoService.save(topico);
        topicoService.save(topico2);
    }

    //GETS E SETS
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
