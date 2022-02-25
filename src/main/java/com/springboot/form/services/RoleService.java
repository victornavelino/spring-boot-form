package com.springboot.form.services;

import java.util.List;

import com.springboot.form.models.entidades.Role;

public interface RoleService {

	public List<Role> listar();
	public Role obtenerPorId(Integer id);
	
}
