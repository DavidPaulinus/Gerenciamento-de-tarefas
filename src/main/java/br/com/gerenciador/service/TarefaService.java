package br.com.gerenciador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import br.com.gerenciador.model.Tarefa;
import br.com.gerenciador.model.DTO.TarefaDTO;
import br.com.gerenciador.service.util.repository.TarefaRepository;

@Service
public class TarefaService {
	@Autowired
	private TarefaRepository repo;

	public Tarefa cadastrar(TarefaDTO dto) {
		var resp = new Tarefa(dto);
		repo.save(resp);

		return resp;
	}

	public Page<Tarefa> listar() {
		return new PageImpl<Tarefa>(repo.findAll());
	}

	public Tarefa detalharPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Tarefa Não encontrada"));
	}
}
