package com.springboot.form.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.springboot.form.editors.PaisPropertyEditor;
import com.springboot.form.models.entidades.Pais;
import com.springboot.form.models.entidades.Usuario;
import com.springboot.form.services.PaisService;
import com.springboot.form.services.PaisServiceImpl;
import com.springboot.form.validadores.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormularioController {

	@Autowired
	private UsuarioValidador usuarioValidador;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(usuarioValidador);
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		formatoFecha.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(formatoFecha, false));
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisService.listar();
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROL_ADMIN");
		roles.add("ROL_USUARIO");
		roles.add("ROL_MODERADOR");
		return roles;
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
