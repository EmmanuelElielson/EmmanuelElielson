package br.com.esig.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoSituacao {
	
	ANDAMENTO("Andamento"),
	CONCLUIDA("Concluida");
	
	@Getter
	private String descricao;

}
