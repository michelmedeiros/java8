package br.com.java8.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grupo {

	
	private Integer id;
	private String descricao;
	
	private Set<Usuario> usuarios = new HashSet<Usuario>();
	
	public void addUsuariosGrupo(List<Usuario> usuariosGrupo){
		this.usuarios.addAll(usuariosGrupo);
	}
	
	public Set<Usuario> getUsuarios(){
		return Collections.unmodifiableSet(this.usuarios);
	}

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

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", this.id, this.descricao); 
	}
	
}
