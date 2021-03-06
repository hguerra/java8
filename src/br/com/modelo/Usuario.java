package br.com.modelo;

public class Usuario {
	private String nome;
	private int pontos;
	private boolean moderador;

	public Usuario(String nome, int pontos) {
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = false;
	}
	
	public Usuario(String nome, int pontos, boolean moderador) {
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = moderador;
	}
	
	public Usuario() {
		this.moderador = false;
	}
	
	public Usuario(String nome) {
		this.nome = nome;
		this.moderador = false;
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

	public boolean isModerador() {
		return moderador;
	}

	public void tornarModerador() {
		this.moderador = true;
	}

	public String toString() {
		return "Usuario " + nome;
	}

}
