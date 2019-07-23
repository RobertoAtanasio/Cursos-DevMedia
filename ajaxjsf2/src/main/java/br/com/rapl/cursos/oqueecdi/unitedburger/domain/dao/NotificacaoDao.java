package br.com.rapl.cursos.oqueecdi.unitedburger.domain.dao;

import javax.persistence.EntityManager;

import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Notificacao;

public class NotificacaoDao {

	public void cadastrar(Notificacao notificacao) {
		EntityManager em = JpaUtil.getParceriasEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(notificacao);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();

			e.printStackTrace();

			// o managedBean é quem vai tratar a Exception
			throw new RuntimeException("Erro ao realizar cadastro da notificacao");
		} finally {
			em.close();
		}
	}
}
