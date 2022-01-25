package br.com.esig.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.esig.enums.TipoSituacao;
import br.com.esig.model.Tarefa;
import br.com.esig.utils.JPAUtils;

public class DaoTarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Tarefa> listaTodasTarefas() {
		EntityManager entityManager = JPAUtils.getEntityManager();
		return entityManager.createQuery("SELECT t FROM Tarefa t order by id", Tarefa.class).getResultList();
	}

	public void deletar(Tarefa tarefa) {
		EntityManager entityManager = JPAUtils.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Object id = JPAUtils.getPrimaryKey(tarefa);
		entityManager.createQuery("delete from " + tarefa.getClass().getCanonicalName() + " where id = " + id)
				.executeUpdate();

		entityTransaction.commit();
		entityManager.close();
	}

	public void concluirTarefa(Tarefa tarefa) {
		EntityManager entityManager = JPAUtils.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Object id = JPAUtils.getPrimaryKey(tarefa);
		entityManager
				.createQuery(
						"update " + tarefa.getClass().getCanonicalName() + " set situacao='CONCLUIDA' where id = " + id)
				.executeUpdate();

		entityTransaction.commit();
		entityManager.close();
	}

	public void salvar(Tarefa tarefa) {
		EntityManager entityManager = JPAUtils.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Tarefa novaTarefa = new Tarefa();
		novaTarefa.setTitulo(tarefa.getTitulo());
		novaTarefa.setDescricao(tarefa.getDescricao());
		novaTarefa.setPrioridade(tarefa.getPrioridade());
		novaTarefa.setResponsabilidade(tarefa.getResponsabilidade());
		novaTarefa.setSituacao(TipoSituacao.ANDAMENTO);
		novaTarefa.setDeadline(tarefa.getDeadline());
		entityManager.persist(novaTarefa);
		entityTransaction.commit();
		entityManager.close();
	}

	public void atualizar(Tarefa tarefa) {
		EntityManager entityManager = JPAUtils.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Object id = JPAUtils.getPrimaryKey(tarefa);
		System.out.println(tarefa);
		Date data =  tarefa.getDeadline();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = format.format(data);
		
		String query =  
				"update "+tarefa.getClass().getCanonicalName() +
			    " set titulo='"+tarefa.getTitulo()+"' , descricao='"+tarefa.getDescricao()+
			    "', deadline='"+dataFormatada+"' , prioridade='"+tarefa.getPrioridade()+
			    "', responsabilidade='"+tarefa.getResponsabilidade()+
			    "' where id ="+id;
		entityManager
				.createQuery(query)
				.executeUpdate();

		entityTransaction.commit();
		entityManager.close();
	}
	
	public Tarefa buscarPorId(Tarefa tarefa) {
		EntityManager entityManager = JPAUtils.getEntityManager();
		return entityManager.createQuery("SELECT t FROM Tarefa t WHERE id ="+tarefa.getId(), Tarefa.class).getSingleResult();
	}

	public List<Tarefa> buscarPorSituacao(String situacao) {
		EntityManager entityManager = JPAUtils.getEntityManager();
		return entityManager
				.createQuery("SELECT t FROM Tarefa t WHERE t.situacao='" + situacao + "' order by id", Tarefa.class)
				.getResultList();
	}

}
