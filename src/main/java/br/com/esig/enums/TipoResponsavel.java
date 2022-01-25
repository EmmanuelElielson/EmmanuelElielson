package br.com.esig.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoResponsavel {

	TECNICO("Técnico"),
	ADMINISTRATIVO("Administrativo"),
	FINANCEIRO("Financeiro");
	
	@Getter
	private String descricao;
	
	
}
