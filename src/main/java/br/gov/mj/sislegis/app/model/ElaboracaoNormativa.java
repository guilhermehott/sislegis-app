package br.gov.mj.sislegis.app.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import br.gov.mj.sislegis.app.enumerated.ElaboracaoNormativaNorma;
import br.gov.mj.sislegis.app.enumerated.ElaboracaoNormativaObjeto;
import br.gov.mj.sislegis.app.enumerated.ElaboracaoNormativaSituacao;
import br.gov.mj.sislegis.app.enumerated.ElaboracaoNormativaTipo;
import br.gov.mj.sislegis.app.json.TagJSON;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@XmlRootElement
@Table(name = "elaboracao_normativa")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id") 
public class ElaboracaoNormativa implements AbstractEntity  {
	
	private static final long serialVersionUID = 7722617248451501605L;
	
	public ElaboracaoNormativa() {
	}
	

	public ElaboracaoNormativa(Long id, Date dataRegistro, ElaboracaoNormativaTipo tipo,
			String nup,
			ElaboracaoNormativaObjeto identificacao,
			String nomeAutor,
			String origemDescricao
			) {
		this.id=id;
		this.dataRegistro=dataRegistro;
		this.tipo=tipo;
		this.nup=nup;
		this.identificacao=identificacao;
		this.nomeAutor=nomeAutor;
		this.origemDescricao=origemDescricao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	// Dados preliminares
	
	@Column
	private Date dataRegistro;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private ElaboracaoNormativaTipo tipo;
	
	@Transient
	private String valueTipo;
	
	@Column
	private String nup;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private ElaboracaoNormativaObjeto identificacao;
	
	@Transient
	private String valueIdentificacao;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario autor;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario coAutor;

	@ManyToOne(fetch = FetchType.EAGER)
	private OrigemElaboracaoNormativa origem;

	@ManyToOne(fetch = FetchType.EAGER)
	private AreaConsultada areaConsultada;
	
	@Column
	private String ementa;
	
	// TODO: tags
	
	// Dados de análise e distribuição
	@Column
	private Date dataDistribuicao;
	
	@Column
	private Equipe equipe;
	
	@Column
	private Usuario parecerista;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "elaboracaoNormativa", fetch = FetchType.EAGER)
	private Set<ElaboracaoNormativaConsulta> listaElaboracaoNormativaConsulta;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Comentario> listaComentario;
	
	@Column
	private Comentario comentario;
	
	// Manifestação (por enquanto deixei na mesma entidade para evitar normalização desnecessaria)
	@Column
	private Date dataManifestacao;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private ElaboracaoNormativaNorma elaboracaoNormativaNorma;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "elaboracaoNormativa")
	private Set<TagElaboracaoNormativa> tagsElaboracaoNormativa;
	
	@Column
	private String comentarioManifestacao;
	
	@Column
	private String arquivoManifestacao;
	
	@Column
	private Integer ano;
	
	@Column
	private String numero;
	
	@Column
	private String ementaPreliminar;
	
	@Column
	private Date dataInclusaoSIDOF;
	
	@Column
	private Date dataAssinaturaSIDOF;
	
	@Column
	private StatusSidof statusSidof;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private ElaboracaoNormativaSituacao elaboracaoNormativaSituacao;
	
	@Transient
	private ElaboracaoNormativaConsulta elaboracaoNormativaConsulta;
	
	@Transient
	private String nomeAutor;
	
	@Transient
	private List<TagJSON> tags;
	
	@Transient
	private List<Usuario> pareceristas;
	
	@Transient
	private String origemDescricao;


	public Long getId() {
		return id;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public ElaboracaoNormativaTipo getTipo() {
		return tipo;
	}

	public String getNup() {
		return nup;
	}

	public ElaboracaoNormativaObjeto getIdentificacao() {
		return identificacao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public Usuario getCoAutor() {
		return coAutor;
	}

	public OrigemElaboracaoNormativa getOrigem() {
		return origem;
	}

	public String getEmenta() {
		return ementa;
	}

	public Date getDataDistribuicao() {
		return dataDistribuicao;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public Usuario getParecerista() {
		return parecerista;
	}


	public Date getDataManifestacao() {
		return dataManifestacao;
	}

	public ElaboracaoNormativaNorma getElaboracaoNormativaNorma() {
		return elaboracaoNormativaNorma;
	}

	public String getComentarioManifestacao() {
		return comentarioManifestacao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public void setTipo(ElaboracaoNormativaTipo tipo) {
		this.tipo = tipo;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public void setIdentificacao(ElaboracaoNormativaObjeto identificacao) {
		this.identificacao = identificacao;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public void setCoAutor(Usuario coAutor) {
		this.coAutor = coAutor;
	}

	public void setOrigem(OrigemElaboracaoNormativa origem) {
		this.origem = origem;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public void setDataDistribuicao(Date dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public void setParecerista(Usuario parecerista) {
		this.parecerista = parecerista;
	}

	public void setDataManifestacao(Date dataManifestacao) {
		this.dataManifestacao = dataManifestacao;
	}

	public void setElaboracaoNormativaNorma(
			ElaboracaoNormativaNorma elaboracaoNormativaNorma) {
		this.elaboracaoNormativaNorma = elaboracaoNormativaNorma;
	}

	public void setComentarioManifestacao(String comentarioManifestacao) {
		this.comentarioManifestacao = comentarioManifestacao;
	}

	public Set<ElaboracaoNormativaConsulta> getListaElaboracaoNormativaConsulta() {
		return listaElaboracaoNormativaConsulta;
	}

	public void setListaElaboracaoNormativaConsulta(
			Set<ElaboracaoNormativaConsulta> listaElaboracaoNormativaConsulta) {
		this.listaElaboracaoNormativaConsulta = listaElaboracaoNormativaConsulta;
	}

	public void setElaboracaoNormativaConsulta(
			ElaboracaoNormativaConsulta elaboracaoNormativaConsulta) {
		this.elaboracaoNormativaConsulta = elaboracaoNormativaConsulta;
	}
	
	public String getArquivoManifestacao() {
		return arquivoManifestacao;
	}

	public void setArquivoManifestacao(String arquivoManifestacao) {
		this.arquivoManifestacao = arquivoManifestacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElaboracaoNormativa other = (ElaboracaoNormativa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public List<TagJSON> getTags() {
		return tags;
	}

	public void setTags(List<TagJSON> tags) {
		this.tags = tags;
	}

	public Set<TagElaboracaoNormativa> getTagsElaboracaoNormativa() {
		return tagsElaboracaoNormativa;
	}

	public void setTagsElaboracaoNormativa(
			Set<TagElaboracaoNormativa> tagsElaboracaoNormativa) {
		this.tagsElaboracaoNormativa = tagsElaboracaoNormativa;
	}


	public List<Usuario> getPareceristas() {
		return pareceristas;
	}


	public void setPareceristas(List<Usuario> pareceristas) {
		this.pareceristas = pareceristas;
	}
		
		
	public AreaConsultada getAreaConsultada() {
		return areaConsultada;
	}


	public void setAreaConsultada(AreaConsultada areaConsultada) {
		this.areaConsultada = areaConsultada;
	}


	public String getValueTipo() {
		return Objects.isNull(tipo)?"": tipo.getValue();
	}


	public String getValueIdentificacao() {
		return Objects.isNull(identificacao)?"":identificacao.getValue();
	}


	public String getNomeAutor() {
		return nomeAutor;
	}


	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}


	public String getOrigemDescricao() {
		return origemDescricao;
	}


	public void setOrigemDescricao(String origemDescricao) {
		this.origemDescricao = origemDescricao;
	}


	public StatusSidof getStatusSidof() {
		return statusSidof;
	}


	public void setStatusSidof(StatusSidof statusSidof) {
		this.statusSidof = statusSidof;
	}


	public Set<Comentario> getListaComentario() {
		return listaComentario;
	}


	public void setListaComentario(Set<Comentario> listaComentario) {
		this.listaComentario = listaComentario;
	}


	public Comentario getComentario() {
		return comentario;
	}


	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}


	public Integer getAno() {
		return ano;
	}


	public void setAno(Integer ano) {
		this.ano = ano;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getEmentaPreliminar() {
		return ementaPreliminar;
	}


	public void setEmentaPreliminar(String ementaPreliminar) {
		this.ementaPreliminar = ementaPreliminar;
	}


	public Date getDataInclusaoSIDOF() {
		return dataInclusaoSIDOF;
	}


	public void setDataInclusaoSIDOF(Date dataInclusaoSIDOF) {
		this.dataInclusaoSIDOF = dataInclusaoSIDOF;
	}


	public Date getDataAssinaturaSIDOF() {
		return dataAssinaturaSIDOF;
	}


	public void setDataAssinaturaSIDOF(Date dataAssinaturaSIDOF) {
		this.dataAssinaturaSIDOF = dataAssinaturaSIDOF;
	}


	public ElaboracaoNormativaSituacao getElaboracaoNormativaSituacao() {
		return elaboracaoNormativaSituacao;
	}


	public void setElaboracaoNormativaSituacao(
			ElaboracaoNormativaSituacao elaboracaoNormativaSituacao) {
		this.elaboracaoNormativaSituacao = elaboracaoNormativaSituacao;
	}


	public ElaboracaoNormativaConsulta getElaboracaoNormativaConsulta() {
		return elaboracaoNormativaConsulta;
	}


	public void setValueTipo(String valueTipo) {
		this.valueTipo = valueTipo;
	}


	public void setValueIdentificacao(String valueIdentificacao) {
		this.valueIdentificacao = valueIdentificacao;
	}



	
}