package com.hospitalgenerico.agendamento.model;

import java.util.ArrayList;

public class Usuario {
	protected boolean medico;
	protected String nome;
	protected String senha;
	protected String plano;
	protected int ID;
	protected ArrayList<Consulta> consultas;
	
	private String especialidade;
	private ArrayList<Consulta> lista_de_espera;
	private ArrayList<Avaliacao> avaliacoes;
	
	private int idade;
	
	public Usuario(String nome, String senha, String plano, int ID, boolean medico, String especialidade, int idade) {
		this.nome = nome;
		this.senha = senha;
		this.plano = plano;
		this.ID = ID;
		this.consultas = new ArrayList<Consulta>();
		this.medico = medico;
		this.especialidade = especialidade;
		this.idade = idade;
		this.lista_de_espera = new ArrayList<Consulta>();
		this.avaliacoes = new ArrayList<Avaliacao>();
	}
	
	//set
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setPlano(String plano) {
		this.plano = plano;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void addConsulta(Consulta consulta) {
		consultas.add(consulta);
	}
	
	public void setIdade(int idade){
		this.idade = idade;
	}
	
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	public void addAvaliacao(Avaliacao avaliacao) {
		avaliacoes.add(avaliacao);
	}
	
	public void setAvaliacoes(ArrayList<Avaliacao> avaliacoes) {
		this.avaliacoes= avaliacoes;	
	}
	
	public void addListaDeEspera(Consulta consulta) {
		lista_de_espera.add(consulta);
	}
	
	public void setListaDeEspera(ArrayList<Consulta> lista_de_espera) {
		this.lista_de_espera = lista_de_espera;
	}
	
	//get
	public String getNome() {
		return nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getPlano() {
		return plano;
	}
	
	public int getID() {
		return ID;
	}
	
	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}
	
	public boolean isMedico() {
		return medico;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public String getEspecialidade() {
		return especialidade;
	}
	
	public ArrayList<Consulta> getListaDeEspera() {
		return lista_de_espera;
	}
	
	public ArrayList<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	

}
