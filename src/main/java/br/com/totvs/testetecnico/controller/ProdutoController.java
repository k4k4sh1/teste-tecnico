package br.com.totvs.testetecnico.controller;

import java.math.BigDecimal;

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

import br.com.totvs.testetecnico.model.Produto;
import br.com.totvs.testetecnico.service.ProdutoService;
import br.com.totvs.testetecnico.service.ProdutoService.ProdutoNaoEncontradoException;
import br.com.totvs.testetecnico.util.BigDecimalEditor;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@InitBinder
	public void initBinderCategoria(WebDataBinder binder) {
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
	}

	@GetMapping
	public String produtos(Model model) {
		model.addAttribute("produtos", produtoService.todos());
		return "produto/produtos";
	}

	@GetMapping("/cadastro")
	public String cadastro(Produto produto) {
		return "produto/cadastro";
	}

	@PostMapping
	public String cadastrar(@Valid Produto produto, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "produto/cadastro";
		}
		
		produtoService.salvar(produto);
		redirectAttributes.addFlashAttribute("mensagem_sucesso", "Produto salvo com sucesso!");

		return "redirect:/produtos";
	}
	
	@GetMapping("/{id}")
	public String editar(@PathVariable(name = "id") Long id, Model model, RedirectAttributes redirectAttributes) {
		
		try {
			Produto produto = produtoService.pesquisaPorId(id);
			model.addAttribute("produto", produto);
		} catch (ProdutoNaoEncontradoException e) {
			redirectAttributes.addFlashAttribute("mensagem_alerta", "Produto não encontrado!");
			return "redirect:/produtos";
		}
		
		return "produto/cadastro";
	}

}
