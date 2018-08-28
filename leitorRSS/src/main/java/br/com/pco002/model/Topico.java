package br.com.pco002.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="rss_schema.topico", schema="rss_schema")

//@Table(name="topico")
public class Topico implements GenericEntity, Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false) 
    private Long id;
    
    @Column(name="nome", nullable=false) 
    private String nome;
    
    @Column(name="quantidade", nullable=true) 
    private int quant = 0;
    
    @OneToMany(mappedBy = "topico", targetEntity = Url_topico.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Url_topico> urls = new ArrayList<Url_topico>();
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @JoinTable(name="rss_schema.inscricao" , joinColumns={@JoinColumn(name="topico_id", nullable = false)}, inverseJoinColumns={@JoinColumn(name=" usuario_id", nullable = false)})
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public List<Url_topico> getUrls() {
        return urls;
    }

    public void setUrls(List<Url_topico> urls) {
        this.urls = urls;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + this.quant;
        hash = 41 * hash + Objects.hashCode(this.urls);
        hash = 41 * hash + Objects.hashCode(this.usuarios);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Topico other = (Topico) obj;
        if (this.quant != other.quant) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.urls, other.urls)) {
            return false;
        }
        if (!Objects.equals(this.usuarios, other.usuarios)) {
            return false;
        }
        return true;
    }

}
