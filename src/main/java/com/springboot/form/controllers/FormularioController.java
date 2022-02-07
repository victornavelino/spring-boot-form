package com.springboot.form.controllers;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.springboot.form.models.entidades.Usuario;

@Controller
public class FormularioController {

	@GetMapping({"/form","/"})
	public String form(Model model) {
		model.addAttribute("titulo", "Formulario usuario");
		return "form";
	}

	@PostMapping("/formu")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {

		model.addAttribute("titulo", "Resultado Form");
		model.addAttribute("usuario", usuario);

		return "resultado";
	}

}
