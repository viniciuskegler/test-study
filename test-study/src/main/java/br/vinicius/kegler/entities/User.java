package br.vinicius.kegler.entities;

public class User {

	private String nome;
	
	public User() {}
	
	public User(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}