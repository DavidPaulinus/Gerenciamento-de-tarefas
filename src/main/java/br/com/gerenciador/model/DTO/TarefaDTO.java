package br.com.gerenciador.model.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TarefaDTO(
		@NotBlank
		String cor,
		
		@NotBlank
		String nome,
		
		String descricao,
		
		@NotNull
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
		LocalDateTime data,
		
		@NotNull
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
		LocalDateTime notificacao,
		
		Boolean completo
		) {

}
