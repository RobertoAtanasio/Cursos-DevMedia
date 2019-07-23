package br.com.rapl.cursos.oqueecdi.unitedburger.domain.servico;

import br.com.rapl.cursos.oqueecdi.unitedburger.domain.dao.ContatoDao;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Contato;

public class ContatoService {

	private ContatoDao contatoDao = new ContatoDao();

	public void cadastrar(Contato contato) {
		contatoDao.cadastrar(contato);
	}
}
