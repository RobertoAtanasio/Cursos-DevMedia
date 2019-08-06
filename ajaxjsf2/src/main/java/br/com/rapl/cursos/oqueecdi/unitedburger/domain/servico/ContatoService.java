package br.com.rapl.cursos.oqueecdi.unitedburger.domain.servico;

import javax.inject.Inject;

import br.com.rapl.cursos.oqueecdi.unitedburger.domain.dao.ContatoDao;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Contato;

// Obs.: como a classe ContatoDao tem o construtor simples, podemos injetar a mesma

public class ContatoService {

	@Inject
	private ContatoDao contatoDao;

	public void cadastrar(Contato contato) {
		contatoDao.cadastrar(contato);
	}

}


//import br.com.rapl.cursos.oqueecdi.unitedburger.domain.dao.ContatoDao;
//import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Contato;
//
//public class ContatoService {
//
//	private ContatoDao contatoDao = new ContatoDao();
//
//	public void cadastrar(Contato contato) {
//		contatoDao.cadastrar(contato);
//	}
//
//}