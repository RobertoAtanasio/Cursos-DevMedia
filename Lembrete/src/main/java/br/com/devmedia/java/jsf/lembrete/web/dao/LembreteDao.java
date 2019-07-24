package br.com.devmedia.java.jsf.lembrete.web.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.devmedia.java.jsf.lembrete.web.bean.JpaResourceBean;
import br.com.devmedia.java.jsf.lembrete.web.model.Lembrete;

public class LembreteDao {

	public List<Lembrete> listarTodos() throws Exception {
		EntityManager em = JpaResourceBean.getEntityManagerFactory();
		List<Lembrete> lembretes = null;

		try {
			lembretes = em.createQuery("select l from Lembrete l", Lembrete.class).getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			em.close();
		}

		return lembretes;
	}

	public void inserir(Lembrete lembrete) throws Exception {
		EntityManager em = JpaResourceBean.getEntityManagerFactory();

		try {
			em.getTransaction().begin();
			em.persist(lembrete);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();

			throw new Exception(e);
		} finally {
			em.close();
		}
	}

	public void atualizar(Lembrete lembrete) throws Exception {
		EntityManager em = JpaResourceBean.getEntityManagerFactory();

		try {
			em.getTransaction().begin();
			em.merge(lembrete);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();

			throw new Exception(e);
		} finally {
			em.close();
		}
	}

	public void excluir(long id) throws Exception {
		EntityManager em = JpaResourceBean.getEntityManagerFactory();

		try {
			em.getTransaction().begin();
			Lembrete lembrete = em.find(Lembrete.class, id);
			em.remove(lembrete);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();

			throw new Exception(e);
		} finally {
			em.close();
		}
	}

	public Lembrete selecionar(long id) throws Exception {
		Lembrete lembrete;

		EntityManager em = JpaResourceBean.getEntityManagerFactory();

		try {
			lembrete = em.find(Lembrete.class, id);
		} finally {
			em.close();
		}

		return lembrete;
	}
}
