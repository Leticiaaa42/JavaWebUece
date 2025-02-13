package com.hospitalgenerico.agendamento.model;

import java.util.ArrayList;

public class Usuario {
	protected String nome;
	protected String senha;
	protected String plano;
	protected ArrayList<Consulta> consultas;
	
	public Usuario(String nome, String senha, String plano) {
		this.nome = nome;
		this.senha = senha;
		this.plano = plano;
		this.consultas = new ArrayList<Consulta>();
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
	
	public void addConsulta(Consulta consulta) {
		consultas.add(consulta);
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
	
	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}
}
