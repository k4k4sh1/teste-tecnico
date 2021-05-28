package br.com.totvs.testetecnico.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "item_pedido")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class ItemPedido extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Positive
	private Long quantidade;
	
	@Getter(AccessLevel.NONE)
	@Transient
	private BigDecimal total;
	
	public BigDecimal getTotal() {
		if (produto != null && produto.getPreco() != null && quantidade != null &&
				quantidade > 0) {
			return produto.getPreco().multiply(new BigDecimal(quantidade));
		}
		return new BigDecimal("0.00");
	}
	
}
