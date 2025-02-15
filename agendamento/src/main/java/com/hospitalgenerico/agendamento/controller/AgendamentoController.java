package com.hospitalgenerico.agendamento.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalgenerico.agendamento.model.Avaliacao;
import com.hospitalgenerico.agendamento.model.Consulta;
import com.hospitalgenerico.agendamento.model.ConsultaRealizada;
import com.hospitalgenerico.agendamento.model.LoginForm;
import com.hospitalgenerico.agendamento.model.MedicoVisivel;
import com.hospitalgenerico.agendamento.model.Usuario;
import com.hospitalgenerico.agendamento.service.AgendamentoService;

@RestController
@RequestMapping("/api/usuarios")
public class AgendamentoController {

	private final AgendamentoService agendamentoService;
	
	public AgendamentoController(AgendamentoService agendamentoService) {
		this.agendamentoService = agendamentoService;
	}
	
	//MANIPULAÇÃO DE USUÁRIOS----------------------------------------

	@PostMapping("/registrar-usuario")
	public ResponseEntity<Usuario> registrarUser(@RequestBody Usuario usuario){
		Usuario novo_user = agendamentoService.registrarUsuario(usuario);
		if (novo_user == null) {
			return ResponseEntity.internalServerError().build();
		}
		else return ResponseEntity.ok(novo_user);
	}
	
	@GetMapping("/login")
	public ResponseEntity<Usuario> loginUser(@RequestBody LoginForm loginform) {
		Usuario user_logado = agendamentoService.loginUser(loginform);
		if (user_logado == null) {
			return ResponseEntity.notFound().build();
		}
		else return ResponseEntity.ok(user_logado);
	}
	
	@PutMapping("/editar-usuario")
	public ResponseEntity<Void> editarUser(@RequestBody Usuario user_editado){
		int resposta = agendamentoService.editarUser(user_editado);
		if (resposta == 404) {
			return ResponseEntity.notFound().build();
		}
		else if (resposta == 200) {
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/apagar-usuario")
	public ResponseEntity<Void> apagarUser(@RequestBody Usuario user_apagado){
		int resposta = agendamentoService.apagarUser(user_apagado);
		if (resposta == 404) {
			return ResponseEntity.notFound().build();
		}
		else if (resposta == 200) {
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	//SERVIÇOS PARA TODOS-----------------------------------
	
	@DeleteMapping("/cancelar-consulta/{IDconsulta}")
	public ResponseEntity<Void> cancelarConsulta(@RequestBody Usuario user, @PathVariable int IDconsulta){
		int resposta = agendamentoService.cancelarConsulta(user, IDconsulta);
		if (resposta == 404) {
			return ResponseEntity.notFound().build();
		}
		else if (resposta == 200) {
			return ResponseEntity.ok().build();
		}
		else if (resposta == 400) {
			return ResponseEntity.badRequest().build();
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/ver-avaliacoes/{nome_medico}")
	public ResponseEntity<ArrayList<Avaliacao>> verAvaliacoes(@RequestBody Usuario user, @PathVariable String nome_medico){
		ArrayList<Avaliacao> resultados = agendamentoService.verAvaliacoes(user, nome_medico);
		
		if (resultados == null) {
			return ResponseEntity.badRequest().build();
		}
		else return ResponseEntity.ok(resultados);
	}
	
	//SERVIÇOS PARA PACIENTES-------------------------------
	
	@PostMapping("/agendar")
	public ResponseEntity<Usuario> agendarConsulta(@RequestBody Consulta consulta){
		Usuario user_atualizado = agendamentoService.agendarConsulta(consulta);
		if (user_atualizado == null) {
			return ResponseEntity.internalServerError().build();
		}
		else return ResponseEntity.ok(user_atualizado);
	}
	
	@PostMapping("/avaliar")
	public ResponseEntity<Void> avaliarConsulta(@RequestBody Avaliacao avaliacao){
		int resposta = agendamentoService.avaliarConsulta(avaliacao);
		if (resposta == 404) {
			return ResponseEntity.notFound().build();
		}
		else if (resposta == 200) {
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/pesquisar-nome/{nome}")
	public ResponseEntity<ArrayList<MedicoVisivel>> pesquisarPorNomeNoPlano(@RequestBody Usuario user, @PathVariable String nome){
		ArrayList<MedicoVisivel> resposta = agendamentoService.pesquisarPorNomeNoPlano(user, nome);
		if (resposta == null) {
			return ResponseEntity.badRequest().build();
		}
		else {
			return ResponseEntity.ok(resposta);
		}
	}
	
	@GetMapping("/pesquisar-especialidade/{especialidade_medico}")
	public ResponseEntity<ArrayList<MedicoVisivel>> pesquisarPorEspecialidadeNoPlano(@RequestBody Usuario user, @PathVariable String especialidade_medico){
		ArrayList<MedicoVisivel> resposta = agendamentoService.pesquisarPorEspecialidadeNoPlano(user, especialidade_medico);
		if (resposta == null) {
			return ResponseEntity.badRequest().build();
		}
		else {
			return ResponseEntity.ok(resposta);
		}
	}
	
	
	//SERVIÇOS PARA MÉDICOS---------------------------------
	
	@PutMapping("/registrar-consulta")
	public ResponseEntity<Void> realizarConsulta(@RequestBody ConsultaRealizada dados){
		int resposta = agendamentoService.realizarConsulta(dados);
		if (resposta == 404) {
			return ResponseEntity.notFound().build();
		}
		else if (resposta == 200) {
			return ResponseEntity.ok().build();
		}
		else if (resposta == 400) {
			return ResponseEntity.badRequest().build();
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	
	
	
}
