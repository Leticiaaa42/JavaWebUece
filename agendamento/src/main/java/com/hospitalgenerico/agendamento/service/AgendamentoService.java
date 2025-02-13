package com.hospitalgenerico.agendamento.service;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import org.springframework.stereotype.Service;

import com.hospitalgenerico.agendamento.model.Usuario;

@Service
public class AgendamentoService {
	private List<Usuario> user_list;
	private File arquivo;
	
	public AgendamentoService() {//importa dados salvos em arquivo
		arquivo = new File("arquivo.txt");
		
		try {
			FileInputStream readData = new FileInputStream("arquivo.txt");
			ObjectInputStream readStream = new ObjectInputStream(readData);

			user_list = (ArrayList<Usuario>) readStream.readObject();
			readStream.close();
			
		}
		
		catch (FileNotFoundException e){
			user_list = new ArrayList<>();
		}
		catch (Exception e) {
			//¯\_(ツ)_/¯
		}
	}
	
	public boolean salvarDados() {
		try {
			FileOutputStream writeData = new FileOutputStream(arquivo);
			ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

			writeStream.writeObject(user_list);
			writeStream.flush();
			writeStream.close();
			return true;
		}
		catch (Exception e){
			return false;
		}
	}	
	
	public int registrarUser(Usuario usuario) {
		user_list.add(usuario);
		
		if (salvarDados()) {
			return 200;
		}
		else {
			return 500;
		}
	}
	
	public Usuario loginUser(String nome, String senha) {
		for (Usuario usuario : user_list) {
			if (usuario.getNome().equalsIgnoreCase(nome) && usuario.getSenha().equalsIgnoreCase(senha)) {
				return usuario;
			}
		}
		return null;
	}
	
	public boolean verificarUsuario(Usuario usuario) {
		//verificar se usuario existe na user list
	}
	
	public int editarUser(Usuario usuario_editado) {
		int index = 0;
		for (Usuario usuario : user_list) {
			if (usuario.getNome().equalsIgnoreCase(usuario_editado.getNome()) && usuario.getSenha().equalsIgnoreCase(usuario_editado.getSenha())) {
				user_list.set(index, usuario_editado);
				break;
			}
			index++;
		}
		
		if (salvarDados()) {
			return 200;
		}
		else {
			return 500;
		}
	}
	

}
