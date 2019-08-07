package br.com.rapl.webservice.service;

import java.util.List;

import br.com.rapl.webservice.model.dao.ProjetoDAO;
import br.com.rapl.webservice.model.domain.Projeto;

public class ProjetoService {

	private ProjetoDAO dao = new ProjetoDAO();

    public List<Projeto> getProjetos() {
        return dao.getAll();
    }

    public Projeto getProjeto(Long id) {
        return dao.getById(id);
    }

    public Projeto saveProjeto(Projeto projeto) {
        return dao.save(projeto);
    }

    public Projeto updateProjeto(Projeto projeto) {
        return dao.update(projeto);
    }

    public Projeto deleteProjeto(Long id) {
        return dao.delete(id);
    }

    public List<Projeto> getProjetosByPagination(int firstResult, int maxResults) {
        return dao.getByPagination(firstResult, maxResults);
    }

    public List<Projeto> getProjetosByName(String name) {
        return dao.getByName(name);
    }
}
