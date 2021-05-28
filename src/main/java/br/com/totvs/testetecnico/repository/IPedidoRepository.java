package br.com.totvs.testetecnico.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.totvs.testetecnico.model.Pedido;

public interface IPedidoRepository extends CrudRepository<Pedido, Long> {
	List<Pedido> findByOrderByDataDesc();
}
