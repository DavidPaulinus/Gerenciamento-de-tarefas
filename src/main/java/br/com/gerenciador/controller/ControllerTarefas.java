package br.com.gerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gerenciador.model.DTO.TarefaDTO;
import br.com.gerenciador.model.DTO.TarefaDetalharDTO;
import br.com.gerenciador.service.TarefaService;
import jakarta.transaction.Transactional;

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
	
}
