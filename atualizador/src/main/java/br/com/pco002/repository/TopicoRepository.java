package br.com.pco002.repository;
import br.com.pco002.model.Topico;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class TopicoRepository extends GenericRepository<Topico>{

    public List<Topico> getAll() {
        CriteriaBuilder builder = super.getManager().getCriteriaBuilder();
        CriteriaQuery<Topico> criteria = builder.createQuery(Topico.class);
        criteria.from(Topico.class);
        return super.getManager().createQuery(criteria).getResultList();
    }
    
}
