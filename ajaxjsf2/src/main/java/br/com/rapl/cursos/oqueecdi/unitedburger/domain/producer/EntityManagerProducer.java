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
//		A classe JpaUtil é substituída por esta classe.
//		As anotações avisa à CDI sobre estes métodos.
//		O objeto no parâmetro é obtido pelo método produtor.
//		@ApplicationScoped - dura enquanto a aplicação estiver ativa.
//		@RequestScoped - dura apenas o tempo da requisição. Existe a cada ação de persistência.

//		O EntityManagerFactory e EntityManager são interfaces, cujas implementações concretas são
//		obtidas de forma específicas.

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
	
	// o @Any foi incluído porque nesta aplicação temos 2 bancos de dados. E por ser igual o método
	// de fechar, colocamos a anotação @Any
	
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
