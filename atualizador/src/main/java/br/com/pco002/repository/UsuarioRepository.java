package br.com.pco002.repository;
import br.com.pco002.model.Usuario;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class UsuarioRepository extends GenericRepository<Usuario>{

    public List<Usuario> getAll() {
        CriteriaBuilder builder = super.getManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
        criteria.from(Usuario.class);
        return super.getManager().createQuery(criteria).getResultList();
    }
    
}
