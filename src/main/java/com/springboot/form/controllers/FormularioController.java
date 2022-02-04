package com.springboot.form.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormularioController {

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("titulo","Formulario usuario");
		return "form";
	}
	@PostMapping
	public String procesar(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String email) {
		model.addAttribute("titulo","Resultado Form");
		model.addAttribute("username",username);
		model.addAttribute("password", password);
		model.addAttribute("email", email);		
		return "resultado";
	}
	
		
	
}
