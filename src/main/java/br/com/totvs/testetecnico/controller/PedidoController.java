package br.com.totvs.testetecnico.controller;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.totvs.testetecnico.model.ItemPedido;
import br.com.totvs.testetecnico.model.Pedido;
import br.com.totvs.testetecnico.model.Situacao;
import br.com.totvs.testetecnico.service.PedidoService;
import br.com.totvs.testetecnico.service.PedidoService.PedidoFinalizadoException;
import br.com.totvs.testetecnico.service.PedidoService.PedidoNaoEncontradoException;
import br.com.totvs.testetecnico.service.ProdutoService;
import br.com.totvs.testetecnico.util.BigDecimalEditor;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ProdutoService produtoService;

	@InitBinder
	public void initBinderCategoria(WebDataBinder binder) {
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
	}

	@GetMapping
	public String pedidos(Model model) {
		model.addAttribute("pedidos", pedidoService.todos());
		return "pedido/pedidos";
	}

	@GetMapping("/cadastro")
	public String cadastro(Pedido pedido, Model model) {
		pedido.setItensPedido(Arrays.asList(new ItemPedido()));
		pedido.setSituacao(Situacao.ABERTO);
		model.addAttribute("produtos", produtoService.todos());
		return "pedido/cadastro";
	}

	@PostMapping
	public String cadastrar(@Valid Pedido pedido, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("produtos", produtoService.todos());
			return "pedido/cadastro";
		}
		
		try {
			pedidoService.salvar(pedido);
			redirectAttributes.addFlashAttribute("mensagem_sucesso", "Pedido salvo com sucesso!");
		} catch (PedidoFinalizadoException e) {
			redirectAttributes.addFlashAttribute("mensagem_alerta", "Pedido já finalizado!");
			return "redirect:/pedidos";
		}

		return "redirect:/pedidos";
	}

	@GetMapping("/{id}")
	public String editar(@PathVariable(name = "id") Long id, Model model, RedirectAttributes redirectAttributes) {

		try {
			Pedido pedido = pedidoService.pesquisaPorId(id);
			model.addAttribute("pedido", pedido);
			model.addAttribute("produtos", produtoService.todos());
			model.addAttribute("bloqueado", pedido.getSituacao() == Situacao.FINALIZADO);
		} catch (PedidoNaoEncontradoException e) {
			redirectAttributes.addFlashAttribute("mensagem_alerta", "Pedido não encontrado!");
			return "redirect:/pedidos";
		}

		return "pedido/cadastro";
	}

}
