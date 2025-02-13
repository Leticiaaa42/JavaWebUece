package com.hospitalgenerico.agendamento.model;

public class Avaliacao {
	private int nota;
	private String texto;
	private String autor;

	public Avaliacao(int nota, String texto, String autor) {
		this.nota = nota;
		this.texto = texto;
		this.autor = autor;
	}
	
	//get
	public int getNota() {
		return nota;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public String getAutor() {
		return autor;
	}
}
