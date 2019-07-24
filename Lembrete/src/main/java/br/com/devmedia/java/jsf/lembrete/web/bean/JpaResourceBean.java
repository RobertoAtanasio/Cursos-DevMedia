package br.com.devmedia.java.jsf.lembrete.web.bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaResourceBean {
	
	private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManagerFactory() {
        if (entityManagerFactory == null) {
        	System.out.println(">>> Criando o EntityManagerFactory...");
            entityManagerFactory = Persistence.createEntityManagerFactory("DBlembrete");
            System.out.println(">>> EntityManagerFactory criado.");
        }
        return entityManagerFactory.createEntityManager();
    }
}
