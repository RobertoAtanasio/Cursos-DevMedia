package br.com.rapl.webservice.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.rapl.webservice.exceptions.DAOException;
import br.com.rapl.webservice.exceptions.ErrorCode;
import br.com.rapl.webservice.model.domain.Empregado;
import br.com.rapl.webservice.model.domain.Projeto;

public class ProjetoEmpregadoDAO {

	public void saveRelationship(long projetoId, long empregadoId) {
        if((projetoId <= 0) || (empregadoId <= 0)) {
            throw new DAOException("O id do projeto e o id do empregado precisam ser maiores do que 0.", ErrorCode.BAD_REQUEST.getCode());
        }

        EntityManager em = JPAUtil.getEntityManager();

        if(relationshipExist(projetoId, empregadoId, em)) {
            throw new DAOException("Relacionamento solicitado para criação já existe.", ErrorCode.CONFLICT.getCode());
        }

        try {
            Projeto projeto = em.find(Projeto.class, projetoId);
            Empregado empregado = em.find(Empregado.class, empregadoId);

            if (empregado == null) {
                throw new NullPointerException();
            }

            em.getTransaction().begin();
            projeto.getEmpregados().add(empregado);
            em.persist(projeto);
            em.getTransaction().commit();
        } catch (NullPointerException ex) {
            em.getTransaction().rollback();
            throw new DAOException("Id do projeto ou do empregado não existe.", ErrorCode.NOT_FOUND.getCode());
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
            throw new DAOException("Erro ao salvar relacionamento entre projeto e empregado no banco de dados: " + ex.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }

    }

    public List<Empregado> getEmpregados(long projetoId) {
        if(projetoId <= 0) {
            throw new DAOException("O id do projeto precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
        }

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery("select e from Empregado e JOIN e.projetos p where p.id = :projetoId", Empregado.class)
                    .setParameter("projetoId", projetoId)
                    .getResultList();
        } catch (RuntimeException ex) {
            throw new DAOException("Erro ao recuperar os empregados do projeto de id " + projetoId + " do banco: " + ex.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }
    }

    public List<Projeto> getProjetos(long empregadoId) {
        if(empregadoId <= 0) {
            throw new DAOException("O id do empregado precisa ser maior do que 0.", ErrorCode.BAD_REQUEST.getCode());
        }

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery("select p from Projeto p JOIN p.empregados e where e.id = :empregadoId", Projeto.class)
                    .setParameter("empregadoId", empregadoId)
                    .getResultList();
        } catch (RuntimeException ex) {
            throw new DAOException("Erro ao recuperar os projetos em que o empregado de id " + empregadoId + " estÃ¡ trabalhando: " + ex.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }
    }

    public void deleteRelationship(long projetoId, long empregadoId) {
        if((projetoId <= 0) || (empregadoId <= 0)) {
            throw new DAOException("O id do projeto e o id do empregado precisam ser maiores do que 0.", ErrorCode.BAD_REQUEST.getCode());
        }

        EntityManager em = JPAUtil.getEntityManager();

        if(!relationshipExist(projetoId, empregadoId, em)) {
            throw new DAOException("Relacionamento informado para remoçã£o não existe.", ErrorCode.NOT_FOUND.getCode());
        }

        try {
            Projeto projeto = em.find(Projeto.class, projetoId);
            Empregado empregado = em.find(Empregado.class, empregadoId);

            em.getTransaction().begin();
            // O remove abaixo remove os relacionamentos em ambas as partes. Não é preciso fazer algo semelhante
            // para a classe empregado.
            projeto.getEmpregados().remove(empregado);
            em.persist(projeto);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            throw new DAOException("Erro ao remover relacionamento entre projeto e empregado no banco de dados: " + ex.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }
    }

    private boolean relationshipExist(long projetoId, long empregadoId, EntityManager em) {
        try {
            em.createQuery("select e from Empregado e join e.projetos p where p.id = :projetoId and e.id = :empregadoId", Empregado.class)
                    .setParameter("projetoId", projetoId)
                    .setParameter("empregadoId", empregadoId)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }
}
