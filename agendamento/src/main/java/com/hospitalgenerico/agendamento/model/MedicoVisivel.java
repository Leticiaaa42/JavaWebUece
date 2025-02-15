package com.hospitalgenerico.agendamento.model;

public class MedicoVisivel {
	private int ID;
	private String nome;
	private String especialidade;

	public MedicoVisivel(int ID, String nome, String especialidade) {
		this.ID = ID;
		this.nome = nome;
		this.especialidade = especialidade;
	}
	
	public int getID(){
		return ID;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getEspecialidade() {
		return especialidade;
	}

}
