package com.springboot.form.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.springboot.form.models.entidades.Usuario;

@Controller
public class FormularioController {

	@GetMapping({ "/form", "/" })
	public String form(Model model) {
		model.addAttribute("titulo", "Formulario usuario");
		return "form";
	}

	@PostMapping("/formu")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {
		
		model.addAttribute("titulo", "Resultado Form");
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err ->{
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "form";
		}

		model.addAttribute("usuario", usuario);

		return "resultado";
	}

}
