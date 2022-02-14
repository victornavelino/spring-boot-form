package com.springboot.form.validadores;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springboot.form.models.entidades.Usuario;

@Component
public class UsuarioValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
//		Usuario usuario = (Usuario)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Requerido.usuario.nombre");
		
//		if(!usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")) {
			
//			errors.rejectValue("identificador", "Pattern.usuario.identificador");
//		}
	}

}
