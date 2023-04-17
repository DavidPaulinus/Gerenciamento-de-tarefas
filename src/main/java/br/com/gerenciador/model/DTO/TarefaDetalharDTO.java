package br.com.gerenciador.model.DTO;

import java.time.LocalDateTime;

import br.com.gerenciador.model.Tarefa;
import br.com.gerenciador.service.util.Conversor;

public record TarefaDetalharDTO(String cor, String nome, String descricao, LocalDateTime data,LocalDateTime notificacao, String completo) {
	public TarefaDetalharDTO(Tarefa ta) {
		this(ta.getCor(), ta.getNome(), ta.getDescricao(), ta.getData(), ta.getNotificacao(), Conversor.converter(ta.getCompleto()));
	}
}
