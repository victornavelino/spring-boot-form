package com.springboot.form.services;

import java.util.List;

import com.springboot.form.models.entidades.Pais;

public interface PaisService {
	
	public List<Pais> listar();
	public Pais obtenerPorId(Integer id);
	

}
