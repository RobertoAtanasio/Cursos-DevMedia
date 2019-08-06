package br.com.rapl.cursos.oqueecdi.unitedburger.domain.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Contato;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.qualifier.UnitedBurgerQualifier;

// o close do EntityManager fica sob responsabilidade do CDI

public class ContatoDao {

	@Inject
	@UnitedBurgerQualifier
	private EntityManager em;
	
	public void cadastrar(Contato contato) {

		try {
			em.getTransaction().begin();
			em.persist(contato);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();

			e.printStackTrace();

			// o managedBean é quem vai tratar a Exception
			throw new RuntimeException("Erro ao realizar cadastro do contato");
		} 
	}
}

//import javax.persistence.EntityManager;
//
//import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Contato;
//
//public class ContatoDao {
//
//	public void cadastrar(Contato contato) {
//		EntityManager em = JpaUtil.getUnitedBurgerEntityManager();
//
//		try {
//			em.getTransaction().begin();
//			em.persist(contato);
//			em.getTransaction().commit();
//		} catch (RuntimeException e) {
//			em.getTransaction().rollback();
//
//			e.printStackTrace();
//
//			// o managedBean é quem vai tratar a Exception
//			throw new RuntimeException("Erro ao realizar cadastro do contato");
//		} finally {
//			em.close();
//		}
//	}
//}
