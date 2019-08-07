package br.com.rapl.webservice.service;

import java.util.List;

import br.com.rapl.webservice.model.dao.ProjetoEmpregadoDAO;
import br.com.rapl.webservice.model.domain.Empregado;
import br.com.rapl.webservice.model.domain.Projeto;

public class RelationshipService {

	private ProjetoEmpregadoDAO dao = new ProjetoEmpregadoDAO();

    public void saveRelationshipProjetoEmpregado(long projetoId, long empregadoId) {
        dao.saveRelationship(projetoId, empregadoId);
    }

    public List<Empregado> getEmpregados(long projetoId) {
        return dao.getEmpregados(projetoId);
    }

    public List<Projeto> getProjetos(long empregadoId) {
        return dao.getProjetos(empregadoId);
    }

    public void deleteRelationshipProjetoEmpregado(long projetoId, long empregadoId) {
        dao.deleteRelationship(projetoId, empregadoId);
    }
}
