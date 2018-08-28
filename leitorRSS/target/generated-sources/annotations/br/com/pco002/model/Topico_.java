package br.com.pco002.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Topico.class)
public abstract class Topico_ {

	public static volatile ListAttribute<Topico, Url_topico> urls;
	public static volatile SingularAttribute<Topico, String> nome;
	public static volatile SingularAttribute<Topico, Long> id;
	public static volatile SingularAttribute<Topico, Integer> quant;
	public static volatile ListAttribute<Topico, Usuario> usuarios;

}

