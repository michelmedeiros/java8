package br.com.java8.utils;

import java.util.HashSet;
import java.util.Set;

import br.com.java8.model.Usuario;

public class GrupoDTO {
	
	private Integer id;
	private String descricao;
	
	private Set<Usuario> usuarios = new HashSet<Usuario>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
