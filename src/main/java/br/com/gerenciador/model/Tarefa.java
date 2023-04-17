package br.com.gerenciador.model;

import java.time.LocalDateTime;

import br.com.gerenciador.model.DTO.TarefaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_tarefas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cor;
	private String nome;
	private String descricao;
	private LocalDateTime data;
	private LocalDateTime notificacao;
	private Boolean completo;

	public Tarefa(TarefaDTO dto) {
		this.cor = dto.cor();
		this.nome = dto.nome();
		this.descricao = dto.descricao();
		this.data = dto.data();
		this.notificacao = dto.notificacao();
		this.completo = false;
	}

	public void atualizar(TarefaDTO dto) {
		this.cor = dto.cor();
		this.nome = dto.nome();
		this.descricao = dto.descricao();
		this.data = dto.data();
		this.notificacao = dto.notificacao();
		this.completo = dto.completo();
	}

}
