package com.hospitalgenerico.agendamento.model;

public class Avaliacao {
	private int nota;
	private String texto;
	private int IDconsulta;
	private int IDautor;

	public Avaliacao(int nota, String texto, int IDconsulta, int IDautor) {
		this.nota = nota;
		this.texto = texto;
		this.IDconsulta = IDconsulta;
		this.IDautor = IDautor;
	}
	
	//get
	public int getNota() {
		return nota;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public int getIDconsulta() {
		return IDconsulta;
	}
	
	public int getIDautor() {
		return IDautor;
	}
	
	//set
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setIDconsulta(int IDconsulta) {
		this.IDconsulta = IDconsulta;
	}
	
	public void setIDautor(int IDautor) {
		this.IDautor = IDautor;
	}
}
