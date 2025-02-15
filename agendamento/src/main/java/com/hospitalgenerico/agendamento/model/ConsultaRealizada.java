package com.hospitalgenerico.agendamento.model;

public class ConsultaRealizada {
	private int IDconsulta;
	private String nome_medico;
	private String sintomas;
	private String tratamento;
	private String exames;
	private String adicional;
	
	public ConsultaRealizada(int IDconsulta, String nome_medico, String sintomas, String tratamento, String exames, String adicional) {
		this.IDconsulta = IDconsulta;
		this.nome_medico = nome_medico;
		this.sintomas = sintomas;
		this.tratamento = tratamento;
		this.exames = exames;
		this.adicional = adicional;
	}
	
	//set
	public void setIDconsulta(int IDconsulta) {
		this.IDconsulta = IDconsulta;
	}
	
	public void setNomeMedico(String nome_medico) {
		this.nome_medico = nome_medico;
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
	
	public int getIDconsulta(){
		return IDconsulta;
	}
	
	public String getNomeMedico() {
		return nome_medico;
	}
	
	public String getSintomas(){
		return sintomas;
	}
	
	public String getTratamento(){
		return tratamento;
	}
	
	public String getExames(){
		return exames;
	}
	
	public String getAdicional(){
		return adicional;
	}

}
