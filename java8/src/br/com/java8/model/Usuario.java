package br.com.java8.model;

/**
 * @author michel
 *
 */
public class Usuario {
	
	private String nome;
	private int pontos;
	private boolean moderador;
	private Grupo grupo;
	
	
	public Usuario(){
		super();
	}

	public Usuario(String nome){
		this.nome = nome;
	}
	
	public Usuario(String nome, int pontos){
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = false;
	}
	
	public Usuario(String nome, int pontos, boolean isModerador){
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = isModerador;
	}
	
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
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	
	@Override
	public String toString() {
		return this.nome + " - " + this.pontos;
	}

}
