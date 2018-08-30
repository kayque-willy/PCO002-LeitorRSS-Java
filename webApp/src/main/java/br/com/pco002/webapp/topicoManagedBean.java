package br.com.pco002.webapp;

import br.com.pco002.model.Topico;
import br.com.pco002.model.Url_topico;
import br.com.pco002.service.TopicoService;
import br.com.pco002.leitor.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

@ManagedBean(name = "topicoFeed")
@SessionScoped
public class topicoManagedBean {

    //PARAMETROS DO WEBSERVICE
    private static final String urlWsdl = "http://localhost:8080/leitorRSS/LeitorRSS?wsdl";
    private static final String urlService = "http://leitor.pco002.com.br/";
    private static final String contextService = "leitorRSS";
    private static URL url;
    private static QName qname;
    private static Service ws;
    private static LeitorRSS leitor;

    //ATRIBUTOS DO JAVA BEAN
    @Inject()
    private TopicoService topicoService;
    private Topico topico = new Topico();
    private List<Feed> feedLista = new ArrayList<Feed>();
    private List<FeedMessage> mensagensFeed = new ArrayList<FeedMessage>();

    public topicoManagedBean() {
        this.topicoService = new TopicoService();
        iniciarLeitor();
        consultarTopico();
        if (topico != null) {
            lerFeedTopico();
            gerarListaMensagens();
            ordenarLista();
        }
    }

    //Consulta o tópico pelo ID passado por parâmetro
    private void consultarTopico() {
        String topicoId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("topicoId");
        if (topicoId != null) {
            topico = topicoService.findById(Long.valueOf(topicoId));
            if (topico == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum tópico selecionado!", "Erro ao verificar atualizações"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum tópico selecionado!", "Erro ao verificar atualizações"));
        }
    }

    //Inicializa os parametros pra conectar ao webservice
    private void iniciarLeitor() {
        try {
            url = new URL(urlWsdl);
            qname = new QName(urlService, contextService);
            ws = Service.create(url, qname);
            leitor = ws.getPort(LeitorRSS.class);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    //Le o feed do topico utilizando o webservice
    private void lerFeedTopico() {
        List<String> urlList = new ArrayList<String>();
        try {
            for (Url_topico u : topico.getUrls()) {
                urlList.add(u.getUrl());
            }
            feedLista = new ArrayList<Feed>();
            feedLista = leitor.readTopic(urlList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Organiza a lista das mensages para apresentação
    private void gerarListaMensagens() {
        for (Feed feed : feedLista) {
            List<FeedMessage> mensagens = feed.getEntries().getEntrie();
            mensagensFeed.addAll(mensagens);
        }
    }

    //Reeordena a lista de mensagens por data
    private void ordenarLista() {
        Collections.sort(mensagensFeed, new Comparator<FeedMessage>() {
            public int compare(FeedMessage o1, FeedMessage o2) {
                return o1.getDate().toGregorianCalendar().getTime().compareTo(o2.getDate().toGregorianCalendar().getTime());
            }
        });
        Collections.reverse(mensagensFeed);
    }

    //GETS E SETS
    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public List<Feed> getFeedLista() {
        return feedLista;
    }

    public void setFeedLista(List<Feed> feedLista) {
        this.feedLista = feedLista;
    }

    public List<FeedMessage> getMensagensFeed() {
        return mensagensFeed;
    }

    public void setMensagensFeed(List<FeedMessage> mensagensFeed) {
        this.mensagensFeed = mensagensFeed;
    }
}
