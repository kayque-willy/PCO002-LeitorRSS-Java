package br.com.pco002.repository;

import br.com.pco002.model.Inscricao;
import br.com.pco002.model.Notificacao;
import br.com.pco002.model.Topico;
import br.com.pco002.model.Usuario;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public class TopicoRepository extends GenericRepository<Topico> {

    private final CriteriaQuery<Topico> criteriaQuery = getCriteriaBuilder().createQuery(Topico.class);

    //Consulta todos os topicos 
    public List<Topico> getAll() {
        criteriaQuery.from(Topico.class);
        return super.getManager().createQuery(criteriaQuery).getResultList();
    }

    //Consulta as notificações por inscrição
    public List<Notificacao> getNotificacao(Inscricao inscricao) {
        TypedQuery<Notificacao> query = getManager().
                createQuery("SELECT n FROM Notificacao n WHERE n.inscricao = :inscricao",Notificacao.class);
        query.setParameter("inscricao", inscricao);
        return query.getResultList();
        
//        CriteriaBuilder builder = getManager().getCriteriaBuilder();
//        CriteriaQuery<Notificacao> criteria = builder.createQuery(Notificacao.class);
//        Metamodel metalModel = getManager().getMetamodel();
//        EntityType<Notificacao> Notificacao_ = metalModel.entity(Notificacao.class);
//        
//        Root<Notificacao> notificacoes =  criteria.from(Notificacao.class);
//        criteria.select(notificacoes);
//        criteria.where(getCriteriaBuilder().equal(notificacoes.get("inscricao"), inscricao));
//        
//        return getManager().createQuery(criteria).getResultList();
    }

}
