package br.com.gerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gerenciador.model.DTO.TarefaDTO;
import br.com.gerenciador.model.DTO.TarefaDetalharDTO;
import br.com.gerenciador.service.TarefaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class ControllerTarefas {
	@Autowired
	private TarefaService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<TarefaDetalharDTO> criarTarefa(@RequestBody TarefaDTO dto, UriComponentsBuilder uri){
		var resp = service.cadastrar(dto);
		
		return ResponseEntity.created(uri.path("/tarefas")
							.buildAndExpand(resp.getId())
							.toUri()
							).body(new TarefaDetalharDTO(resp));
		
	}
	
	@GetMapping
	public ResponseEntity<Page<TarefaDetalharDTO>> listarTarefas(){
		var lista = service.listar();
		return ResponseEntity.ok(lista.map(TarefaDetalharDTO::new));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TarefaDetalharDTO> detalharTarefa(@PathVariable Long id){
		var taref = service.detalharPorId(id);
		
		return ResponseEntity.ok(new TarefaDetalharDTO(taref));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TarefaDetalharDTO> editarTarefa(@RequestBody @Valid TarefaDTO dto, @PathVariable Long id){
		var resp  = service.editar(dto, id);
		
		return ResponseEntity.ok(new TarefaDetalharDTO(resp));
	}
	
	@PutMapping("/{id}/concluido")
	@Transactional
	public ResponseEntity<TarefaDetalharDTO> concluirTarefa(@PathVariable Long id){
		var resp = service.concluir(id);
		
		return ResponseEntity.ok(new TarefaDetalharDTO(resp));
	}
	@PutMapping("/{id}/fazer")
	@Transactional
	public ResponseEntity<TarefaDetalharDTO> fazerTarefa(@PathVariable Long id){
		var resp = service.fazer(id);
		
		return ResponseEntity.ok(new TarefaDetalharDTO(resp));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<String> deletarTarefa(@PathVariable Long id) {
		var resp = service.deletarPorId(id);
		return ResponseEntity.ok(resp);
	}
	
}
