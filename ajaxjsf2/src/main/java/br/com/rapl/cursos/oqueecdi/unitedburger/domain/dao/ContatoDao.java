package br.com.rapl.cursos.oqueecdi.unitedburger.domain.dao;

import javax.persistence.EntityManager;

import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Contato;

public class ContatoDao {

	public void cadastrar(Contato contato) {
	    EntityManager em = JpaUtil.getUnitedBurgerEntityManager();

	    try {
	      em.getTransaction().begin();
	      em.persist(contato);
	      em.getTransaction().commit();
	    } catch (RuntimeException e) {
	      em.getTransaction().rollback();

	      e.printStackTrace();

	      // o managedBean é quem vai tratar a Exception
	      throw new RuntimeException("Erro ao realizar cadastro do contato");
	    } finally {
	      em.close();
	    }
	  }
}
