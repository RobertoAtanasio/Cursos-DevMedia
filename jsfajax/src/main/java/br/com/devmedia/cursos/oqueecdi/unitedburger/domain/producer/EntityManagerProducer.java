package br.com.devmedia.cursos.oqueecdi.unitedburger.domain.producer;

import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.qualifier.ParceriasQualifier;
import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.qualifier.UnitedBurgerQualifier;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    public void fecharEntityManager(@Disposes @Any EntityManager manager) {
        String url = (String) manager.getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url");

        System.out.println(url);

        if (manager.isOpen()) {
            manager.close();
        }
    }
}
