package br.com.totvs.testetecnico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.totvs.testetecnico.model.Produto;
import br.com.totvs.testetecnico.repository.IProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private IProdutoRepository produtoRepository;
	
	public void salvar(Produto produto) {
		produtoRepository.save(produto);
	}
	
	public List<Produto> todos() {
		return produtoRepository.findByOrderByNome();
	}
	
	public Produto pesquisaPorId(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ProdutoNaoEncontradoException());
	}
	
	public class ProdutoNaoEncontradoException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
	
}
