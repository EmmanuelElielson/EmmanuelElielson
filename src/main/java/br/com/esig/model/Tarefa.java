package br.com.esig.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.esig.enums.TipoPrioridade;
import br.com.esig.enums.TipoResponsavel;
import br.com.esig.enums.TipoSituacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Tarefa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TipoSituacao situacao;
	
	@Enumerated(EnumType.STRING)
	private TipoPrioridade prioridade;
	
	@Enumerated(EnumType.STRING)
	private TipoResponsavel responsabilidade;
	
	@Temporal(TemporalType.DATE)
	private Date deadline;
}
