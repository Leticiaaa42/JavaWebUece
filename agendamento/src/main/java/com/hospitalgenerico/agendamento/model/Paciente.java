package com.hospitalgenerico.agendamento.model;

public class Paciente extends Usuario {
	private int idade;

	public Paciente(String nome, String senha, String plano, int idade) {
		super(nome, senha, plano);
		this.idade = idade;
	}

	//set
	public void setIdade(int idade){
		this.idade = idade;
	}
	
	//get
	public int getIdade() {
		return idade;
	}
}
