
import br.com.pco002.leitor.LeitorRSS;
import br.com.pco002.model.*;
import br.com.pco002.service.TopicoService;
import br.com.pco002.util.FaceUtil;
import java.util.ArrayList;
import java.util.List;

public class TesteRSS {

    public static void main(String[] args) {
//
//        TopicoService topicoService = new TopicoService();
//        Topico topico = topicoService.findById(29L);
//        
//        LeitorRSS rss = new LeitorRSS();
//        rss.readTopic(topico);
        
//-----------------------------------------INSERT----------------------------------------
//        for (int i = 0; i < 2; i++) {
//
//            Topico topico = new Topico();
//            topico.setNome("g1 - noticias gerais " + i);
//
//            //URL
//            List<Url_topico> urls = new ArrayList<Url_topico>();
//
//            Url_topico url = new Url_topico();
//            url.setUrl("http://pox.globo.com/rss/g1/ " + i);
//            urls.add(url);
//            url.setTopico(topico);
//
//            url = new Url_topico();
//            url.setUrl("http://pox.globo.com/rss/g1/educacao/ " + i);
//            urls.add(url);
//            url.setTopico(topico);
//
//            //SALVANDO NO TOPICO
//            topico.setUrls(urls);
//
//            //USUARIOS
//            List<Usuario> usuarios = new ArrayList<Usuario>();
//
//            Usuario usuario = new Usuario();
//            usuario.setNome("Kayque " + i);
//            usuario.setEmail("kayque@email.com");
//            usuario.setTipo(FaceUtil.User);
//            usuarios.add(usuario);
//
//            usuario = new Usuario();
//            usuario.setNome("Kayque " + (i + 1));
//            usuario.setEmail("kayque@email.com");
//            usuario.setTipo(FaceUtil.User);
//            usuarios.add(usuario);
//
//            //SALVANDO TOPICO
//            topico.setUsuarios(usuarios);
//
//            //SALVANDO NO BANCO
//            topicoService.save(topico);
//        }

//-----------------------------------DATA---------------------------------------------------
//        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
//        Date dataTeste = null;
//        try {
//            dataTeste = format.parse("Fri, 15 Aug 2018 13:06:37 -0300");
//        } catch (ParseException ex) {
//            Logger.getLogger(TesteRSS.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        LeitorRSS rss = new LeitorRSS();
//        Feed feed = rss.readFeed("http://www.contabeis.com.br/rss/noticias/");
//        System.out.println(feed);
//        for (FeedMessage message : feed.getMessages()) {
//            if (message.getDate().after(dataTeste)) {
//                System.out.println(message.getDate() + " maior que " + dataTeste);
//            }
//            System.out.println(message.getDate());
//        }

        System.exit(0);
    }
}
