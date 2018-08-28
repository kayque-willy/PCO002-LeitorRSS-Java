package br.com.pco002.atualizador;

import br.com.pco002.leitor.Feed;
import br.com.pco002.leitor.FeedMessage;
import br.com.pco002.leitor.LeitorRSS;
import br.com.pco002.model.Inscricao;
import br.com.pco002.model.Notificacao;
import br.com.pco002.model.Topico;
import br.com.pco002.model.Url_topico;
import br.com.pco002.model.Usuario;
import br.com.pco002.service.TopicoService;
import br.com.pco002.util.FaceUtil;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Atualizador {

    private static final String urlWsdl = "http://localhost:8080/leitorRSS/LeitorRSS?wsdl";
    private static final String urlService = "http://leitor.pco002.com.br/";
    private static final String contextService = "leitorRSS";

    private static URL url;
    private static QName qname;
    private static Service ws;
    private static LeitorRSS leitor;

    private static TopicoService topicoService = new TopicoService();
    private static List<Topico> topicoList = new ArrayList<Topico>();
    private static List<Feed> feedLista = new ArrayList<Feed>();

    //-----------------------------MAIN--------------------------------
    public static void main(String[] args) {
        Timer timer = null;
        long TEMPO = (1000 * 30); // chama o método a cada 30 segundos 
        System.out.println("Iniciando a atualização de notificações...");
        iniciarLeitor();
        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa;
            tarefa = new TimerTask() {
                public void run() {
//                    insert();
                    System.out.println("Consultando tópicos...");
                    consultarTopicos();
                    System.out.println("Atualizando tópicos...");
                    for (Topico topico : topicoList) {
                        try {
                            lerFeedTopico(topico);
                            verificarAtualizacao(topico);
                        } catch (MalformedURLException ex) {
                            System.out.println("Erro no atualizador");
                            Logger.getLogger(Atualizador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }
    }

    //-------------------METODOS CONSULTA FEED-------------------------
    //-----Inicializa os parametros pra conectar ao webservice---------
    public static void iniciarLeitor() {
        try {
//            URL url = new URL("http://localhost:8080/leitorRSS/LeitorRSS?wsdl");
//            QName qname = new QName("http://leitor.pco002.com.br/", "leitorRSS");
//            Service ws = Service.create(url, qname);
//            LeitorRSS leitorRss = ws.getPort(LeitorRSS.class);
//
//            List<String> urlList = new ArrayList<String>();
//            urlList.add("http://pox.globo.com/rss/g1/");
//            
//            feedLista = new ArrayList<Feed>();
//            feedLista = leitorRss.readTopic(urlList);
            url = new URL(urlWsdl);
            qname = new QName(urlService, contextService);
            ws = Service.create(url, qname);
            leitor = ws.getPort(LeitorRSS.class);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Atualizador.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    //-------------Consulta todos os tópicos no banco------------------
    private static void consultarTopicos() {
        topicoList = new ArrayList<Topico>();
        topicoList = topicoService.getAll();
    }

    //-----------Carrega o conjunto de feed das urls do topico---------
    private static void lerFeedTopico(Topico topico) throws MalformedURLException {
        List<String> urlList = new ArrayList<String>();
        for (Url_topico u : topico.getUrls()) {
            urlList.add(u.getUrl());
        }
        feedLista = new ArrayList<Feed>();
        feedLista = leitor.readTopic(urlList);
    }

    //Verifica se existem novas atualizações para o topico e gera as notificaões
    private static void verificarAtualizacao(Topico topico) {
        int quant = 0;
        for (Feed feed : feedLista) {
            for (FeedMessage feedMessage : feed.getEntries().getEntrie()) {
                if (feedMessage.getDate().toGregorianCalendar().getTime().after(topico.getUltimaAtualizacao())) {
                    quant++;
                }
            }
        }
        if (quant > 0) {
            System.out.println("Encontradas: " + quant + " atualizações para o topico: " + topico.getNome() + " as " + new Date());
            for (Inscricao inscricao : topico.getInscricao()) {
                Notificacao notificacao = new Notificacao();
                notificacao.setData(new Date());
                notificacao.setQuant(quant);
                notificacao.setInscricao(inscricao);
                inscricao.getNotificacao().add(notificacao);
            }
            topico.setUltimaAtualizacao(new Date());
            topicoService.save(topico);
        } else {
            System.out.println("Nenhuma atualização para o topico: " + topico.getNome() + " as " + new Date());
        }

    }

    //----------------------INSERT-------------------------------------
    private static void insert() {
        for (int i = 1; i < 2; i++) {
            //CRIA O TOPICO
            Topico topico = new Topico();
            topico.setNome("g1 2 - noticias gerais " + i);
            topico.setUltimaAtualizacao(new Date());

            //CRIA LISTA DE URLS
            List<Url_topico> urls = new ArrayList<Url_topico>();

            //CRIA UMA URL
            Url_topico url = new Url_topico();
//            url.setUrl("http://pox.globo.com/rss/g1/");
            url.setUrl("http://pox.globo.com/rss/g1/ciencia-e-saude/");
            urls.add(url);
            url.setTopico(topico);

            //CRIA A SEGUNDA URL
            url = new Url_topico();
//            url.setUrl("http://pox.globo.com/rss/g1/educacao/");
            url.setUrl("http://pox.globo.com/rss/g1/economia/");
            urls.add(url);
            url.setTopico(topico);

            //SALVA A LISTA DE URLS NO TOPICO
            topico.setUrls(urls);

            //CRIA A LISTA DE USUARIOS
            List<Usuario> usuarios = new ArrayList<Usuario>();
            //CRIA A LISTA DE INSCRIÇÕES
            List<Inscricao> inscricoes = new ArrayList<Inscricao>();

            //CRIA O PRIMEIRO USUARIO
            Usuario usuario = new Usuario();
            usuario.setNome("Kayque " + i);
            usuario.setEmail("kayque@email.com");
            usuario.setTipo(FaceUtil.User);
            usuarios.add(usuario);

            //CRIA UMA INSCRIÇÃO PARA O PRIMEIRO USUARIO
            Inscricao inscricao = new Inscricao();
            inscricao.setTopico(topico);
            inscricao.setUsuario(usuario);
            inscricoes.add(inscricao);

            //CRIA O SEGUNDO USUARIO
            usuario = new Usuario();
            usuario.setNome("Kayque " + (i + 1));
            usuario.setEmail("kayque@email.com");
            usuario.setTipo(FaceUtil.User);
            usuarios.add(usuario);

            //CRIA UMA INSCRIÇÃO PARA O SEGUNDO USUARIO
            inscricao = new Inscricao();
            inscricao.setTopico(topico);
            inscricao.setUsuario(usuario);
            inscricoes.add(inscricao);

            //SALVA A LISTA DE INSCRIÇÕES NO TOPICO
            topico.setInscricao(inscricoes);

            //SALVANDO NO BANCO
            topicoService.save(topico);
        }
    }

}
