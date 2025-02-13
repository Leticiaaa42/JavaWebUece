package com.hospitalgenerico.agendamento.model;

import java.util.ArrayList;

public class Medico extends Usuario {
	private String especialidade;
	private int nota;
	private ArrayList<Consulta> lista_de_espera;
	private ArrayList<Avaliacao> avaliacoes;
	
	public Medico(String nome, String senha, String plano, String especialidade) {
		super(nome, senha, plano);
		this.especialidade = especialidade;
		this.nota = 3;
		this.lista_de_espera = new ArrayList<Consulta>();
		this.avaliacoes = new ArrayList<Avaliacao>();
	}
	
	//set
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	//get

	public String getEspecialidade() {
		return especialidade;
	}
	
	public int getNota() {
		return nota;
	}
	
	public ArrayList<Consulta> getListaDeEspera() {
		return lista_de_espera;
	}
	
	public ArrayList<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
}
