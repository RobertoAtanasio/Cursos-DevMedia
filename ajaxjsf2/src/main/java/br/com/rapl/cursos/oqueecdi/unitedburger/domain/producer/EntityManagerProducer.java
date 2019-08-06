package br.com.rapl.cursos.oqueecdi.unitedburger.domain.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.rapl.cursos.oqueecdi.unitedburger.domain.qualifier.ParceriasQualifier;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.qualifier.UnitedBurgerQualifier;

// Obs.: 
//		A classe JpaUtil � substitu�da por esta classe.
//		As anota��es avisa � CDI sobre estes m�todos.
//		O objeto no par�metro � obtido pelo m�todo produtor.
//		@ApplicationScoped - dura enquanto a aplica��o estiver ativa.
//		@RequestScoped - dura apenas o tempo da requisi��o. Existe a cada a��o de persist�ncia.

//		O EntityManagerFactory e EntityManager s�o interfaces, cujas implementa��es concretas s�o
//		obtidas de forma espec�ficas.

public class EntityManagerProducer {

	@Produces
	@ApplicationScoped
	@UnitedBurgerQualifier
	public EntityManagerFactory getUnitedBurgerEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("unitedburger");
    }
	
	@Produces
	@RequestScoped
	@UnitedBurgerQualifier
	public EntityManager getUnitedBurgerEntityManager(@UnitedBurgerQualifier EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
	
	@Produces
	@ApplicationScoped
	@ParceriasQualifier
	public EntityManagerFactory getParceriasEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("parcerias");
    }
	
	@Produces
	@RequestScoped
	@ParceriasQualifier
	public EntityManager getParceriasEntityManager(@ParceriasQualifier EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
	
	// o @Any foi inclu�do porque nesta aplica��o temos 2 bancos de dados. E por ser igual o m�todo
	// de fechar, colocamos a anota��o @Any
	
	public void fecharEntityManager(@Disposes @Any EntityManager manager) {
		// display do banco atual
		String url = (String) manager.getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url");
        System.out.println(url);

        if (manager.isOpen()) {
            manager.close();
        }
    }
}


//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class JpaUtil {
//
//	private static EntityManagerFactory unitedburgerEmf;
//	private static EntityManagerFactory parceriasEmf;
//
//	public static EntityManager getUnitedBurgerEntityManager() {
//		if (unitedburgerEmf == null) {
//			unitedburgerEmf = Persistence.createEntityManagerFactory("unitedburger");
//		}
//
//		return unitedburgerEmf.createEntityManager();
//	}
//
//	public static EntityManager getParceriasEntityManager() {
//		if (parceriasEmf == null) {
//			parceriasEmf = Persistence.createEntityManagerFactory("parcerias");
//		}
//
//		return parceriasEmf.createEntityManager();
//	}
//}
