package com.hospitalgenerico.agendamento.model;

public class LoginForm {
	private String nome;
	private String senha;

	public LoginForm(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getSenha() {
		return senha;
	}

}
