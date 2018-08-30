package br.com.pco002.webapp;

import br.com.pco002.model.Topico;
import br.com.pco002.model.Url_topico;
import br.com.pco002.service.TopicoService;
import br.com.pco002.leitor.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private List<Feed> feedLista = new ArrayList<Feed>();

    //ATRIBUTOS DO JAVA BEAN
    @Inject()
    private TopicoService topicoService;
    private Topico topico = new Topico();

    public topicoManagedBean() {
        this.topicoService = new TopicoService();
        iniciarLeitor();
        consultarTopico();
        if (topico != null) {
            lerFeedTopico();
        }
        feedLista.get(0);
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

    //GETS E SETS
    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

}
