package br.com.gerenciador.service;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import br.com.gerenciador.model.Tarefa;
import br.com.gerenciador.model.DTO.TarefaDTO;
import br.com.gerenciador.service.util.repository.TarefaRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TarefaServiceTest {
	@MockBean
	private TarefaRepository repo;
	
	@Autowired
	private TarefaService serv;
	
	private TarefaDTO dto = new TarefaDTO("Cor", "Nome", "Descrição", LocalDateTime.now(), LocalDateTime.now().plusDays(2), false);
	private TarefaDTO dto2 = new TarefaDTO("Cor", "Nome", "Descrição", LocalDateTime.now(), LocalDateTime.now().plusDays(2), true);
	private TarefaDTO dtoEdit = new TarefaDTO("Cor", "Nome", "Descrição 2", LocalDateTime.now(), LocalDateTime.now().plusDays(2), false);
	
	@Test
	@DisplayName("deve cadastrar Tarefa com TarefaDTO como parametro no construtor e retornar um tipo Tarefa")
	void cadastra() {
		var resp = serv.cadastrar(dto);
		
		assertInstanceOf(Tarefa.class, resp);
	}
	@Test
	@DisplayName("deve listar todas as tarefas que estão no banco de dados")
	void listar() {
		var resp = serv.listar();
		
		assertInstanceOf(Page.class, resp);
	}
	@Test
	@DisplayName("deve detalhar a tarefa no banco de dados que corresponde o ID passado como parâmetro")
	void detalhar() {
		var t = serv.cadastrar(dto);
		var resp = serv.detalharPorId(t.getId());
		
		assertInstanceOf(Tarefa.class, resp);
	}
	@Test
	@DisplayName("deve editar a tarefa no banco de dados que corresponde o ID passado como parâmetro")
	void editar() {
		var t = serv.cadastrar(dto);
		var resp = serv.editar(dtoEdit, t.getId());
		
		assertInstanceOf(Tarefa.class, resp);
	}
	@Test
	@DisplayName("deve concluir a tarefa no banco de dados que corresponde o ID passado como parâmetro")
	void concluir() {
		var t = serv.cadastrar(dto);
		var resp = serv.concluir(t.getId());
		
		assertInstanceOf(Tarefa.class, resp);
	}
	@Test
	@DisplayName("deve deixar a fazer a tarefa no banco de dados que corresponde o ID passado como parâmetro")
	void fazer() {
		serv.cadastrar(dto2);
		var resp = serv.concluir(1l);
		
		assertInstanceOf(Tarefa.class, resp);
	}
	@Test
	@DisplayName("deve deletar a tarefa no banco de dados que corresponde o ID passado como parâmetro")
	void deletarPorId() {
		serv.cadastrar(dto);
		var resp = serv.deletarPorId(1l);
		
		assertInstanceOf(String.class, resp);
	}
	
}
