package br.com.totvs.testetecnico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.totvs.testetecnico.model.Pedido;
import br.com.totvs.testetecnico.model.Situacao;
import br.com.totvs.testetecnico.repository.IPedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public void salvar(Pedido pedido) {
		
		if (pedido.getSituacao() == Situacao.FINALIZADO) {
			throw new PedidoFinalizadoException();
		}
		
		if (pedido.getBotaoFinalizar() != null && pedido.getBotaoFinalizar()) {
			pedido.setSituacao(Situacao.FINALIZADO);
		}
		
		pedido.getItensPedido().forEach(item -> {
			item.setProduto(produtoService.pesquisaPorId(item.getProduto().getId()));
		});
		
		pedidoRepository.save(pedido);
	}
	
	public List<Pedido> todos() {
		return pedidoRepository.findByOrderByDataDesc();
	}
	
	public Pedido pesquisaPorId(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException());
	}
	
	public class PedidoNaoEncontradoException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
	
	public class PedidoFinalizadoException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
	
}
