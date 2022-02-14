package com.springboot.form.controllers;

//import java.util.HashMap;
//import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.form.models.entidades.Usuario;
import com.springboot.form.validadores.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormularioController {
	
	@Autowired
	private UsuarioValidador usuarioValidador;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(usuarioValidador);
	}

	@GetMapping({ "/form", "/" })
	public String form(Model model) {
		Usuario usuario= new Usuario();
		usuario.setNombre("Elian");
		usuario.setApellido("Navelino");
		usuario.setIdentificador("28.780.050-K");
		model.addAttribute("titulo", "Formulario usuario");
		model.addAttribute("usuario",usuario);
		return "form";
	}

	@PostMapping("/formu")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		
		//usuarioValidador.validate(usuario, result);
		model.addAttribute("titulo", "Resultado Form");
		
		if (result.hasErrors()) {
			return "form";
		}

		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "resultado";
	}

}
