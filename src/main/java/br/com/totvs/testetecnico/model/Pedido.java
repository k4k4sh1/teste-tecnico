package br.com.totvs.testetecnico.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import br.com.totvs.testetecnico.validation.EmailChecker;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "pedido")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@NotNull
	@Column(nullable = false, updatable = false, unique = true)
	private Long numero;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;

	@NotNull
	@NotEmpty
	@NotBlank
	@Length(max = 250)
	@Column(length = 250, nullable = false)
	private String razaoSocial;

	@CNPJ
	@NotNull
	@NotEmpty
	@NotBlank
	@Length(min = 18, max = 18)
	@Column(length = 18, nullable = false)
	private String cnpj;

	@NotNull
	@NotEmpty
	@NotBlank
	@Length(min = 15, max = 15)
	@Column(length = 15, nullable = false)
	private String telefone;

	@EmailChecker
	@NotNull
	@NotEmpty
	@NotBlank
	@Length(max = 150)
	@Column(length = 150, nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Situacao situacao;

	@Valid
	@NotEmpty
	@Size(min = 1)
	@JoinColumn(name = "pedido_id")
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private List<ItemPedido> itensPedido = new ArrayList<>();

	@Version
	private Long versao;

	@Transient
	private Long quantidadeProdutos;

	@Transient
	private Long quantidadeItens;

	@Transient
	private BigDecimal totalPedido;
	
	@Transient
	private Boolean botaoFinalizar;

	public Long getQuantidadeProdutos() {
		return itensPedido.stream().filter(item -> item.getProduto() != null && item.getProduto().getId() != null)
				.mapToLong(item -> item.getProduto().getId()).distinct().count();
	}

	public Long getQuantidadeItens() {
		return itensPedido.stream().filter(item -> item.getProduto() != null && item.getProduto().getId() != null)
				.mapToLong(item -> item.getQuantidade()).sum();
	}

	public BigDecimal getTotalPedido() {
		return itensPedido.stream().map(item -> item.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
