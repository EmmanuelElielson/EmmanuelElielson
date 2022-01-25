package br.com.esig.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoPrioridade {
	
	ALTA("Alta"),
	MEDIA("Media"),
	BAIXA("Baixa");
	
	@Getter
	private String descricao;
	
}
