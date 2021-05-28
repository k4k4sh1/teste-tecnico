package br.com.totvs.testetecnico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.totvs.testetecnico.model.Produto;

public interface IProdutoRepository extends CrudRepository<Produto, Long> {
	List<Produto> findByOrderByNome();
	Optional<Produto> findByNome(String nome);
}
