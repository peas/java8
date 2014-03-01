package br.com.casadocodigo.java8;

class Usuario {
	
	private int pontos;
	private String nome;
	private boolean moderador;
	
	public Usuario(String nome) {
		this.nome = nome;
	}

	public Usuario(String nome, int pontos) {
		this.pontos = pontos;
		this.nome = nome;
		this.moderador = false;
	}

	public Usuario(String nome, int pontos, boolean moderador) {
		this.pontos = pontos;
		this.nome = nome;
		this.moderador = moderador;
	}

	public int getPontos() {
		return pontos;
	}

	public String getNome() {
		return nome;
	}

	public void tornaModerador() {
		this.moderador = true;
	}
	
	public String toString() {
		return "Usuario " + nome;
	}

	public boolean isModerador() {
		return moderador;
	}
}