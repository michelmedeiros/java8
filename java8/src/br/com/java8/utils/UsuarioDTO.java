package br.com.java8.utils;

import br.com.java8.model.Grupo;

public class UsuarioDTO {
	
	private String nome;
	private int pontos;
	private boolean moderador;
	private GrupoDTO grupo;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public boolean isModerador() {
		return moderador;
	}
	public void tornaModerador() {
		this.moderador = true;
	}
	public GrupoDTO getGrupo() {
		return grupo;
	}
	public void setGrupo(GrupoDTO grupo) {
		this.grupo = grupo;
	}
	
	

}
