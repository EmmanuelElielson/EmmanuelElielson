package br.com.esig.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;

import br.com.esig.dao.DaoTarefa;
import br.com.esig.enums.TipoPrioridade;
import br.com.esig.enums.TipoResponsavel;
import br.com.esig.enums.TipoSituacao;
import br.com.esig.model.Tarefa;
import lombok.Getter;
import lombok.Setter;


@ViewScoped
@ManagedBean(name = "mBTarefa")
public class TarefaBean {
	
	@Getter
	@Setter
	private String situacao;
	
	@Getter
	@Setter
	private HtmlSelectOneMenu selectSituacao;
	
	@Getter
	@Setter
	private Tarefa tarefa = new Tarefa();
	
	private DaoTarefa daoTarefas = new DaoTarefa();
	
	@Getter
	@Setter
	public boolean editando = false;
	
	@Getter
	@Setter
	private List<Tarefa> tarefas = daoTarefas.listaTodasTarefas();
	
	public TipoPrioridade[] getPrioridades() {
		return TipoPrioridade.values();
	}
	
	public TipoSituacao[] getSituacaos() {
		return TipoSituacao.values();
	}
	
	public TipoResponsavel[] getResponsabilidades() {
		return TipoResponsavel.values();
	}
	
	public void salvar() {
		daoTarefas.salvar(tarefa);
		limpar();
		carregarTarefas();
	}
	
	public void deleta(Tarefa tarefa) {
		daoTarefas.deletar(tarefa);
		carregarTarefas();
	}
	
	public void concluirTarefa(Tarefa tarefa) {
		daoTarefas.concluirTarefa(tarefa);
		carregarTarefas();
	}
	public void carregarTarefas() {
		setTarefas(daoTarefas.listaTodasTarefas());
	}
	
	public void limpar() {
		tarefa = new Tarefa();
	}
	
	public void atualizar() {
		daoTarefas.atualizar(tarefa);
		carregarTarefas();
		editando = false;
		limpar();
	}
	
	public void mudancaSituacao() {
		if(this.situacao != null && !this.situacao.equals("")) {
			setTarefas(daoTarefas.buscarPorSituacao(situacao));
		}else {
			setTarefas(daoTarefas.listaTodasTarefas());
		}
	}
	
	public void buscarTarefaPorId(Tarefa t) {
		tarefa = daoTarefas.buscarPorId(t);
		editando = true;
	}
}
