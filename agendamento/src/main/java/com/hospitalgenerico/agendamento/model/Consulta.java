package com.hospitalgenerico.agendamento.model;

public class Consulta {
	private Medico medico;
	private Paciente paciente;
	private int dia;
	private int mes;
	private int ano;
	
	private boolean concluido;
	private boolean avaliado; //colocar ambos como true para indicar cancelamento
	
	private String sintomas;
	private String tratamento;
	private String exames;
	private String adicional;
	
	public Consulta(Medico medico, Paciente paciente, int dia, int mes, int ano) {
		this.medico = medico;
		this.paciente = paciente;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		
		this.concluido = false;
		this.avaliado = false;
		
		this.sintomas = null;
		this.tratamento = null;
		this.exames = null;
		this.adicional = null;
	}
	
	//set
	public void concuir() {
		this.concluido = true;
	}
	
	public void avaliar() {
		this.avaliado = true;
	}
	
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}
	
	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}
	
	public void setExames(String exames) {
		this.exames = exames;
	}
	
	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}
	
	//get
	public Medico getMedico() {
		return medico;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public int getDia() {
		return dia;
	}
	
	public int getMes() {
		return mes;
	}
	
	public int getAno() {
		return ano;
	}
	
	public boolean isConcluido() {
		return concluido;
	}
	
	public boolean isAvaliado() {
		return avaliado;
	}
	
	public String getSintomas() {
		return sintomas;
	}
	
	public String getTratamento() {
		return tratamento;
	}
	
	public String getExames() {
		return exames;
	}
	
	public String getAdicional() {
		return adicional;
	}
}
