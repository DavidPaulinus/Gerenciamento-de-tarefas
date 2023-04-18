package br.com.gerenciador.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.gerenciador.model.DTO.TarefaDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class TarefaControllerTest {
	@Autowired
	private MockMvc moc;
	
	@Autowired
	private JacksonTester<TarefaDTO> json;
	
	private TarefaDTO dto = new TarefaDTO("Cor", "Nome", "Descrição", LocalDateTime.now(), LocalDateTime.now().plusDays(2), false);
	private TarefaDTO dto2 = new TarefaDTO("Cor", "Nome", "Descrição AAAAAAAAAAAAAAAAAAAAA", LocalDateTime.now(), LocalDateTime.now().plusDays(2), false);
	
	@Test
	@DisplayName("deve retornar 201 por criar tarefa")
	void criarTarefa() throws Exception {
		var jj = json.write(dto).getJson();
		
		moc.perform(MockMvcRequestBuilders.post("/tarefas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
	@Test
	@DisplayName("deve retornar 200 por listar tarefas")
	void listarTarefas() throws Exception {
		moc.perform(MockMvcRequestBuilders.get("/tarefas")
				.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	@DisplayName("deve retornar 200 por detallhar tarefa")
	void detalharTarefa() throws Exception {
		moc.perform(MockMvcRequestBuilders.get("/tarefas/{id}", 1l)
					.contentType(MediaType.APPLICATION_JSON)
					)
					.andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	@DisplayName("deve retornar 200 por editar tarefa")
	void editarTarefa() throws Exception {
		var jj = json.write(dto2).getJson();
		
		moc.perform(MockMvcRequestBuilders.put("/tarefas/{id}", 1l)
					.contentType(MediaType.APPLICATION_JSON)
					.content(jj)
					)
					.andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	@DisplayName("deve retornar 200 por concluir tarefa")
	void concluirTarefa() throws Exception {
		moc.perform(MockMvcRequestBuilders.put("/tarefas/{id}/concluido", 1l)
					.contentType(MediaType.APPLICATION_JSON)
					)
					.andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	@DisplayName("deve retornar 200 por deixar tarefa a fazer")
	void fazerTarefa() throws Exception {
		moc.perform(MockMvcRequestBuilders.put("/tarefas/{id}/fazer", 1l)
					.contentType(MediaType.APPLICATION_JSON)
					)
					.andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	@DisplayName("deve retornar 200 por deletar tarefa por ID")
	void deletarTarefa() throws Exception {
		moc.perform(MockMvcRequestBuilders.delete("/tarefas/{id}", 2l)
					.contentType(MediaType.APPLICATION_JSON)
					)
					.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
}
