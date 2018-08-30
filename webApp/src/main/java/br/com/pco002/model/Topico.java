package br.com.pco002.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity(name="Topico")
@Table(name="topico", schema="rss_schema")
public class Topico implements GenericEntity, Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false) 
    private Long id;
    
    @Column(name="nome", nullable=false) 
    private String nome;
    
    @Column(name="quantidade", nullable=true) 
    private int quant = 0;
    
    @Column(name = "ultima_atualizacao", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ultimaAtualizacao;
    
    @OneToMany(mappedBy = "topico", targetEntity = Url_topico.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Url_topico> urls = new ArrayList<Url_topico>();
    
    @OneToMany(mappedBy = "topico", targetEntity = Inscricao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Inscricao> inscricoes = new ArrayList<Inscricao>();
    
//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
//    @JoinTable(name="rss_schema.inscricao" , joinColumns={@JoinColumn(name="topico_id", nullable = false)}, inverseJoinColumns={@JoinColumn(name=" usuario_id", nullable = false)})
//    private List<Usuario> usuarios = new ArrayList<Usuario>();

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

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public List<Inscricao> getInscricao() {
        return inscricoes;
    }

    public void setInscricao(List<Inscricao> inscricao) {
        this.inscricoes = inscricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + this.quant;
        hash = 67 * hash + Objects.hashCode(this.ultimaAtualizacao);
        hash = 67 * hash + Objects.hashCode(this.urls);
        hash = 67 * hash + Objects.hashCode(this.inscricoes);
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
        if (!Objects.equals(this.ultimaAtualizacao, other.ultimaAtualizacao)) {
            return false;
        }
        if (!Objects.equals(this.urls, other.urls)) {
            return false;
        }
        if (!Objects.equals(this.inscricoes, other.inscricoes)) {
            return false;
        }
        return true;
    }

    
    
}
