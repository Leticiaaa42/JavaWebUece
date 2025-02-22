package com.hospitalgenerico.agendamento.service;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import org.springframework.stereotype.Service;

import com.hospitalgenerico.agendamento.model.Avaliacao;
import com.hospitalgenerico.agendamento.model.Consulta;
import com.hospitalgenerico.agendamento.model.ConsultaRealizada;
import com.hospitalgenerico.agendamento.model.LoginForm;
import com.hospitalgenerico.agendamento.model.MedicoVisivel;
import com.hospitalgenerico.agendamento.model.Usuario;


//===ÍNDICE DOS SERVIÇOS===
// "+"=public  "-"=private

//=====Salvamento de Arquivos=====
//TODO +[contrutor]
//TODO -salvarDados()

//=====Manipulação de Usuários=====
//-getUserIDVazio()
//-getConsultaIDVazio()
//-verificarUsuario()
//+registrarUsuario()
//+loginUser()
//+editarUser()
//+apagarUser()

//=====Pesquisas Internas=====
//-pesquisarUmMedico()
//-pesquisarUmPaciente()
//-pesquisarUmaConsulta()
//-pesquisarMedicoPorNome()
//-pesquisarMedicoPorEspecialidade()

//=====Serviços para Todos=====
//+cancelarConsulta()
//+verAvaliacoes()

//=====Serviços para Pacientes=====
//+agendarConsulta()
//+avaliarConsulta()
//+pesquisarPorNomeNoPlano()
//+pesquisarPorEspecialidadeNoPlano()

//=====Serviços para Médicos=====
//+realizarConsulta()

@Service
public class AgendamentoService {
	private List<Usuario> user_list = new ArrayList<Usuario>();
	private List<Consulta> consulta_list = new ArrayList<Consulta>();
	private File user_data = new File("user_list.data");
  private File consulta_data = new File("consulta_data.data");
	
	//SALVAMENTO DE ARQUIVOS--------------------------------
	
	//isso tem que salvar tanto user_list quanto consulta_list!!!!!!!!!!!!!!!!!!!!!
	
	public AgendamentoService() {//importa dados salvos em arquivo
		
		try {
			FileInputStream readData;
			ObjectInputStream readStream;
      
      if (user_data.exists()) {
        readData = new FileInputStream(user_data);
        readStream = new ObjectInputStream(readData);
        user_list = (ArrayList<Usuario>) readStream.readObject();
        readStream.close();
      } else
        user_data.createNewFile();
		}
		catch (FileNotFoundException e){
      e.printStackTrace();
		}
		catch (Exception e) {
      System.out.println("Erro ao inicializar user_list");
      e.printStackTrace();
		}
		
		try {
			FileInputStream readData;
			ObjectInputStream readStream;

      if (consulta_data.exists()) {
        readData = new FileInputStream(consulta_data);
        readStream = new ObjectInputStream(readData);
        consulta_list = (ArrayList<Consulta>) readStream.readObject();
			  readStream.close();
      } else
        consulta_data.createNewFile();
		}
		catch (FileNotFoundException e){
      e.printStackTrace();
		}
		catch (Exception e) {
      System.out.println("Erro ao inicializar consulta_list");
      e.printStackTrace();
		}
	}
	
	private boolean salvarDados() {//salva os dados relevantes em arquivo, retorna sucesso em boolean
    //Salvar usuarios
    try {
      FileOutputStream userData = new FileOutputStream(user_data, false);
      ObjectOutputStream userStream = new ObjectOutputStream(userData);
      userStream.writeObject(user_list);
      userStream.close();
    }
    catch (Exception e){
      System.out.println("Erro ao salvar dados de usuarios");
      e.printStackTrace();

      return false;
    }

    //Salvar consultas
    try {
      FileOutputStream consultaData;
      ObjectOutputStream consultaStream;
      
      consultaData = new FileOutputStream(consulta_data, false);
      consultaStream = new ObjectOutputStream(consultaData);
      consultaStream.writeObject(consulta_list);
      consultaStream.close();
    }
    catch (Exception e){
      System.out.println("Erro ao salvar dados de consultas");
      e.printStackTrace();

      return false;
    }

    return true;
	}	
	
	//MANIPULAÇÃO DE USUÁRIOS---------------------------------------
	
	private int getUserIDVazio() {
		int menor = 1;
		if (user_list.size() != 0) {
			for (Usuario user : user_list) {
				if ((user.getID() == menor) && (user.getNome() != null)) {
					menor++;
				}
			}
		}
		return menor;
		//note como isso requer que user_list tenha sempre ordem crescente e contínua de IDs
	}
	
	private int getConsultaIDVazio() {
		int menor = 1;
		if (consulta_list.size() != 0) {
			for (Consulta user : consulta_list) {
				if ((user.getIDconsulta() == menor) && (user.getMedico() != null)) {
					menor++;
				}
			}
		}
		return menor;
		//note como isso requer que user_list tenha sempre ordem crescente e contínua de IDs
	}
	
	private int verificarUsuario(Usuario possivel_usuario) {
		boolean achado = false;
		for (Usuario user : user_list) {
			if (user.getID() == possivel_usuario.getID() && possivel_usuario.getSenha().equals(possivel_usuario.getSenha())) {
				achado = true;
			}
		}
		if (!achado) {
			return 0;
		}
		if (possivel_usuario.isMedico()) {
			return 2;
		}
		else {
			return 1;
		}
	}
	
	public Usuario registrarUsuario(Usuario usuario) {
		usuario.setID(getUserIDVazio());
		
		user_list.add(usuario);
		
		salvarDados();
		return usuario;
	}
	
	
	public Usuario loginUser(LoginForm loginform) {
		for (Usuario usuario : user_list) {
			if (usuario.getNome().equalsIgnoreCase(loginform.getNome()) && usuario.getSenha().equalsIgnoreCase(loginform.getSenha())) {
				return usuario;
			}
		}
		return null;
	}
	
	public int editarUser(Usuario usuario_editado) {
		int index = 0;
		boolean achado = false;
		for (Usuario usuario : user_list) {
			if (usuario.getID() == usuario_editado.getID()) {
				user_list.set(index, usuario_editado);
				achado = true;
				break;
			}
			index++;
		}
		
		if (!achado) {
			return 404;
		}
		
		if (salvarDados()) {
			return 200;
		}
		else {
			return 500;
		}
	}
	
	public int apagarUser(Usuario usuario_apagado) {
		Usuario user_nulo = new Usuario(null, null, null, usuario_apagado.getID(), false, null, -1);
		boolean achado = false;
		int index = 0;
		for (Usuario usuario : user_list) {
			if ( usuario.getID() == usuario_apagado.getID()) {
				user_list.set(index, user_nulo);
				achado = true;
				break;
			}
			index++;
		}
		
		if (!achado) {
			return 404;
		}
		
		if (salvarDados()) {
			return 200;
		}
		else {
			return 500;
		}
	}
	
	//PESQUISAS INTERNAS------------------------------------
	
	private Usuario pesquisarUmMedico(String nome) {
		for (Usuario user : user_list) {
			if (user.getNome().equalsIgnoreCase(nome) && user.isMedico()) {
				return user;
			}
		}
		return null;
	}
	
	private Usuario pesquisarUmPaciente(String nome) {
		for (Usuario user : user_list) {
			if (user.getNome().equalsIgnoreCase(nome) && !(user.isMedico())) {
				return user;
			}
		}
		return null;
	}
	
	private Consulta pesquisarUmaConsulta(int ID) {
		for (Consulta consulta : consulta_list) {
			if (consulta.getIDconsulta() == ID) {
				return consulta;
			}
		}
		return null;
	}
	
	private ArrayList<Usuario> pesquisarMedicoPorNome(String nome, String plano){
		ArrayList<Usuario> medicos = new ArrayList<Usuario>();
		for (Usuario user : user_list) {
			if (user.isMedico() == true) {
				medicos.add(user);
			}
		}
		
		ArrayList<Usuario> resultados = new ArrayList<Usuario>();
		for (Usuario user : medicos) {
			if ((user.getNome().equalsIgnoreCase(nome)) && (user.getPlano().equalsIgnoreCase(plano))) {
				resultados.add(user);
			}
		}
		return resultados;
	}
	
	private ArrayList<Usuario> pesquisarMedicoPorEspecialidade(String especialidade, String plano){
		ArrayList<Usuario> medicos = new ArrayList<Usuario>();
		for (Usuario user : user_list) {
			if (user.isMedico() == true) {
				medicos.add(user);
			}
		}
		
		ArrayList<Usuario> resultados = new ArrayList<Usuario>();
		for (Usuario user : medicos) {
			if ((user.getEspecialidade().equalsIgnoreCase(especialidade)) && (user.getPlano().equalsIgnoreCase(plano))) {
				resultados.add(user);
			}
		}
		return resultados;
	}

	//SERVIÇOS PARA TODOS-----------------------------------
	
	public int cancelarConsulta(Usuario user, int IDconsulta) {
		if (verificarUsuario(user) != 0) {
			//achar consulta na consulta_list, também apagar ele na user_list
			Consulta consulta_nula = new Consulta(null, null, -1, -1, -1);
			Consulta consulta = null;
			int index = 0;
			for (Consulta c : consulta_list) {
				if (c.getIDconsulta() == IDconsulta) {
					consulta = c;
					consulta_list.set(index, consulta_nula);
				}
				index++;
			}
			if (consulta == null) {
				return 404;
			}
			
			//apagar consulta nas consultas do paciente
			Usuario paciente = pesquisarUmPaciente(consulta.getPaciente());
			index = 0;
			for (Consulta c : paciente.getConsultas()) {
				if (c.getIDconsulta() == IDconsulta) {
					paciente.getConsultas().set(index, consulta_nula);
				}
				index++;
			}
			
			//ver se a consulta está na lista de espera do médico
			Usuario medico = pesquisarUmMedico(consulta.getMedico());
			index = 0;
			for (Consulta c : medico.getListaDeEspera()) {
				if (c.getIDconsulta() == IDconsulta) {
					medico.getListaDeEspera().set(index, consulta_nula);
					if (salvarDados()) {
						return 200;
					}
					else {
						return 500;
					}
				}
				index++;
			}
			
			//achar na lista principal de consultas e puxar uma consulta da lista de espera
			Consulta consulta_movida = null;
			index = 0;
			for (Consulta c : medico.getListaDeEspera()) {
				if (c.getMedico() != null) {
					consulta_movida = c;
					medico.getListaDeEspera().set(index, consulta_nula);
					break;
				}
				index++;
			}
			
			//se consulta_movida == null, não há o que mover
			if (consulta_movida == null) {
				consulta_movida = consulta_nula;
			}
			
			index = 0;
			for (Consulta c : medico.getConsultas()) {
				if (c.getIDconsulta() == IDconsulta) {
					medico.getConsultas().set(index, consulta_movida);
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
		return 400;
		
		
	}
	
	public ArrayList<Avaliacao> verAvaliacoes(Usuario user, String nome_medico){
		if (verificarUsuario(user) != 0) {
			Usuario medico = pesquisarUmMedico(nome_medico);
			return medico.getAvaliacoes();
		}
		return null;
	}
	
	//SERVIÇOS PARA PACIENTES-------------------------------
	
	public Usuario agendarConsulta(Consulta consulta) { //[similar ao registrarUser()]
		Usuario user = pesquisarUmPaciente(consulta.getPaciente());
		Usuario medico = pesquisarUmMedico(consulta.getMedico());
		if (verificarUsuario(user) == 1) {
			//adiciona o que falta nos dados da consulta
			consulta.setIDconsulta(getConsultaIDVazio());
			consulta.setIDmedico(medico.getID());
			consulta.setIDpaciente(user.getID());
			
			//adiciona a consulta nas consultas do paciente e do medico
			ArrayList<Consulta> consultas_do_medico = medico.getConsultas();
			int consultas_no_msm_dia = 0;
			for (Consulta c : consultas_do_medico) {
				if (c.getDia() == consulta.getDia() && c.getMes() == consulta.getMes() && c.getAno() == consulta.getAno()) {
					consultas_no_msm_dia++;
				}
			}
			if (consultas_no_msm_dia >= 4) {
				medico.addListaDeEspera(consulta);
			}
			else {
				medico.addConsulta(consulta);
			}
			
			user.addConsulta(consulta);
			
			
			
			
			consulta_list.add(consulta);
			
			salvarDados();
			return user;
		}
		return null;
	}
	
	public int avaliarConsulta(Avaliacao avaliacao) {
		for (Consulta consulta : consulta_list) {
			if (consulta.getIDconsulta() == avaliacao.getIDconsulta() && consulta.isConcluido() && !consulta.isAvaliado()) {
				Usuario medico = pesquisarUmMedico(consulta.getMedico());
				Usuario autor = pesquisarUmPaciente(consulta.getPaciente());
				if (medico == null || autor == null || autor.getID() != avaliacao.getIDautor()) {
					return 404;
				}
				
				consulta.avaliar();
				medico.addAvaliacao(avaliacao);
				
				//atualizando agora as consultas nos usuários
				ArrayList<Consulta> consultas_autor = autor.getConsultas();
				consultas_autor.set(consulta.getIDconsulta()-1, consulta);
				
				ArrayList<Consulta> consultas_medico = medico.getConsultas();
				consultas_medico.set(consulta.getIDconsulta()-1, consulta);
				
				if (salvarDados()) {
					return 200;
				}
				else {
					return 500;
				}
			}
		}
		return 404;
	}
	
	public ArrayList<MedicoVisivel> pesquisarPorNomeNoPlano(Usuario user, String nome_medico) {
		if (verificarUsuario(user) == 1) {
			ArrayList<Usuario> resultados = pesquisarMedicoPorNome(nome_medico, user.getPlano());
			ArrayList<MedicoVisivel> previews = new ArrayList<MedicoVisivel>();
			for (Usuario medico : resultados) {
				previews.add(new MedicoVisivel(medico.getID(), medico.getNome(), medico.getEspecialidade()));
			}
			return previews;
		}
		return null;
	}
	
	public ArrayList<MedicoVisivel> pesquisarPorEspecialidadeNoPlano(Usuario user, String especialidade_medico){
		if (verificarUsuario(user) == 1) {
			ArrayList<Usuario> resultados = pesquisarMedicoPorEspecialidade(especialidade_medico, user.getPlano());
			ArrayList<MedicoVisivel> previews = new ArrayList<MedicoVisivel>();
			for (Usuario medico : resultados) {
				previews.add(new MedicoVisivel(medico.getID(), medico.getNome(), medico.getEspecialidade()));
			}
			return previews;
		}
		return null;
	}
	
	//SERVIÇOS PARA MÉDICOS---------------------------------
	
	public int realizarConsulta(ConsultaRealizada dados) {
		Usuario medico = pesquisarUmMedico(dados.getNomeMedico());
		if (medico != null) {
			Consulta consulta = pesquisarUmaConsulta(dados.getIDconsulta());
			if (consulta != null) {
				consulta.concuir();
				consulta.setSintomas(dados.getSintomas());
				consulta.setTratamento(dados.getTratamento());
				consulta.setExames(dados.getExames());
				consulta.setAdicional(dados.getAdicional());
				
				//atualizando agora as consultas nos usuários
				ArrayList<Consulta> consultas_medico = medico.getConsultas();
				consultas_medico.set(consulta.getIDconsulta()-1, consulta);
				
				Usuario paciente = pesquisarUmPaciente(consulta.getPaciente());
				if (paciente == null) {
					return 404;
				}
				ArrayList<Consulta> consultas_paciente = paciente.getConsultas();
				consultas_paciente.set(consulta.getIDconsulta()-1, consulta);
				
				if (salvarDados()) {
					return 200;
				}
				else {
					return 500;
				}
			}
			return 404;
		}
		return 404;
	}
	
	
}
