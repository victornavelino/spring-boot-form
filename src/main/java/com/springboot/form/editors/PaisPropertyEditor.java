package com.springboot.form.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.form.services.PaisService;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private PaisService service;

	@Override
	public void setAsText(String idString) throws IllegalArgumentException {
		if (idString != null && idString.length() > 0) {
			try {
				Integer id = Integer.parseInt(idString);
				setValue(service.obtenerPorId(id));
			} catch (NumberFormatException e) {
				setValue(null);
			}

		} else {
			setValue(null);
		}
	}

}
