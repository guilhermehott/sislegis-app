package br.gov.mj.sislegis.app.model;

import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import br.gov.mj.sislegis.app.enumerated.TipoTarefa;

@Entity
@Table(name = "tarefa")
@XmlRootElement
public class Tarefa extends AbstractEntity {
	public static Tarefa createTarefaEncaminhamento(Usuario usuario, EncaminhamentoProposicao encaminhamento) {
		Tarefa tarefa = new Tarefa(TipoTarefa.ENCAMINHAMENTO, usuario);
		tarefa.encaminhamentoProposicao = encaminhamento;
		return tarefa;
	}

	private static final long serialVersionUID = -806063711060116952L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "data")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private TipoTarefa tipoTarefa;

	@Column
	private boolean isVisualizada;
	@Column
	private boolean isFinalizada;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private EncaminhamentoProposicao encaminhamentoProposicao;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	@Transient
	private Proposicao proposicao;

	Tarefa() {
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Comentario comentarioFinalizacao;

	Tarefa(TipoTarefa tipo, Usuario user) {
		this.tipoTarefa = tipo;
		this.usuario = user;
		this.data = new Date();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoTarefa getTipoTarefa() {
		return tipoTarefa;
	}

	public void setTipoTarefa(TipoTarefa tipoTarefa) {
		this.tipoTarefa = tipoTarefa;
	}

	public boolean isFinalizada() {
		return isFinalizada;
	}

	public void setFinalizada(boolean isFinalizada) {
		this.isFinalizada = isFinalizada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Proposicao getProposicao() {
		return proposicao;
	}

	public void setProposicao(Proposicao proposicao) {
		this.proposicao = proposicao;
	}

	public boolean isVisualizada() {
		return isVisualizada;
	}

	public void setVisualizada(boolean isVisualizada) {
		this.isVisualizada = isVisualizada;
	}

	public EncaminhamentoProposicao getEncaminhamentoProposicao() {
		if (!TipoTarefa.ENCAMINHAMENTO.equals(tipoTarefa)) {
			throw new IllegalArgumentException("Esta tarefa nao foi criada a partir de um encaminhamento");
		}
		return encaminhamentoProposicao;
	}

	public void setEncaminhamentoProposicao(EncaminhamentoProposicao ent) {

		encaminhamentoProposicao = ent;
	}

	public Comentario getComentarioFinalizacao() {
		return comentarioFinalizacao;
	}

	public void setComentarioFinalizacao(Comentario comentarioFinalizacao) {
		this.comentarioFinalizacao = comentarioFinalizacao;
	}
}