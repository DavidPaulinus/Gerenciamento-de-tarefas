package br.com.gerenciador.model.DTO;

import java.time.LocalDateTime;

import br.com.gerenciador.model.Tarefa;

public record TarefaDetalharDTO(String cor, String nome, String descricao, LocalDateTime data,
		LocalDateTime notificacao) {
	public TarefaDetalharDTO(Tarefa ta) {
		this(ta.getCor(), ta.getNome(), ta.getDescricao(), ta.getData(), ta.getNotificacao());
	}
}
