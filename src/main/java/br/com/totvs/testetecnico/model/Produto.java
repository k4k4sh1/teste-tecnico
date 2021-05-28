package br.com.totvs.testetecnico.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "produto")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Produto extends Entidade {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@NotBlank
	@Length(max = 250)
	@Column(length = 250, nullable = false, unique = true)
	private String nome;
	
	@Digits(integer = 6, fraction = 2)
    @DecimalMin(value = "00.01")
    @DecimalMax(value = "999999.99")
    @NotNull
    @Positive
	private BigDecimal preco;
}
