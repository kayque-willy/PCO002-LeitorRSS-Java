package br.com.pco002.repository;

import br.com.pco002.model.Usuario;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UsuarioRepository extends GenericRepository<Usuario> {

    private final CriteriaQuery<Usuario> criteriaQuery = getCriteriaBuilder().createQuery(Usuario.class);

    public List<Usuario> getAll() {
        criteriaQuery.from(Usuario.class);
        return getManager().createQuery(criteriaQuery).getResultList();
    }

    public Usuario findByEmailSenha(String email, String senha) {
        Root<Usuario> usuario = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(usuario);
        criteriaQuery.where(
                getCriteriaBuilder().like(usuario.<String>get("email"), email),
                getCriteriaBuilder().like(usuario.<String>get("senha"), senha)
        );
        return (Usuario) getManager().createQuery(criteriaQuery).getSingleResult();
    }

}
