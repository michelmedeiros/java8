package br.com.java8.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grupo {

	private Set<Usuario> usuarios = new HashSet<Usuario>();
	
	public void addUsuariosGrupo(List<Usuario> usuariosGrupo){
		this.usuarios.addAll(usuariosGrupo);
	}
	
	public Set<Usuario> getUsuarios(){
		return Collections.unmodifiableSet(this.usuarios);
	}
	
}
