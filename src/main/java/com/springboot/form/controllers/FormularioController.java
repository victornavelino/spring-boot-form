package com.springboot.form.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.util.HashMap;
//import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.form.editors.NombreMayusculaEditor;
import com.springboot.form.models.entidades.Pais;
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
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		formatoFecha.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(formatoFecha, false));
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return Arrays.asList(new Pais(1, "AR", "Argentina"),
				new Pais(1, "AR", "Argentina"),
				new Pais(2, "BR", "Brasil"),
				new Pais(3, "BO", "Bolivia"),
				new Pais(4, "PR", "Paraguay"),
				new Pais(5, "CL", "Chile"));
	}
	
	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("Argentina", "Brasil", "Bolivia", "Uruguay", "Paraguay", "Chile");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<String,String>();
		paises.put("AR", "Argentina");
		paises.put("BR", "Brasil");
		paises.put("BO", "Bolivia");
		paises.put("UR", "Uruguay");
		paises.put("PA", "Paraguay");
		paises.put("CL", "Chile");
		return paises;
	}

	@GetMapping({ "/form", "/" })
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Elian");
		usuario.setApellido("Navelino");
		usuario.setIdentificador("28.780.050-K");
		model.addAttribute("titulo", "Formulario usuario");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/formu")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {

		// usuarioValidador.validate(usuario, result);
		model.addAttribute("titulo", "Resultado Form");

		if (result.hasErrors()) {
			return "form";
		}

		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "resultado";
	}

}
