package br.com.totvs.testetecnico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InicioController {

	@GetMapping
	public String inicio() {
		return "index";
	}

}
