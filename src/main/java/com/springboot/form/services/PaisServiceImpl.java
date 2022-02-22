package com.springboot.form.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.form.models.entidades.Pais;

@Service
public class PaisServiceImpl implements PaisService {

	private List<Pais> lista;

	public PaisServiceImpl() {
		//inicializamos lista con datos (despues sera un select de la base)
		this.lista = Arrays.asList(new Pais(1, "AR", "Argentina"), new Pais(1, "AR", "Argentina"),
				new Pais(2, "BR", "Brasil"), new Pais(3, "BO", "Bolivia"), new Pais(4, "PR", "Paraguay"),
				new Pais(5, "CL", "Chile"));
	}

	@Override
	public List<Pais> listar() {

		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado = null;
		for(Pais pais: lista) {
			if(id ==pais.getId()) {
				resultado=pais;
				break;
			}
		}
		return resultado;
	}

}
