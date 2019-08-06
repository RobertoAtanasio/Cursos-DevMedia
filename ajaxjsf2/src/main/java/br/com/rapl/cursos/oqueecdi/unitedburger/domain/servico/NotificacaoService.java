package br.com.rapl.cursos.oqueecdi.unitedburger.domain.servico;

import javax.inject.Inject;

import br.com.rapl.cursos.oqueecdi.unitedburger.domain.dao.NotificacaoDao;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Notificacao;

// Obs.: como a classe NotificacaoDao tem o construtor simples, podemos injetar a mesma

public class NotificacaoService {

	@Inject
	private NotificacaoDao notificacaoDao;

	public void cadastrar(Notificacao notificacao) {
		notificacaoDao.cadastrar(notificacao);
	}
}


//import br.com.rapl.cursos.oqueecdi.unitedburger.domain.dao.NotificacaoDao;
//import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Notificacao;
//
//public class NotificacaoService {
//
//	private NotificacaoDao notificacaoDao = new NotificacaoDao();
//
//	public void cadastrar(Notificacao notificacao) {
//		notificacaoDao.cadastrar(notificacao);
//	}
//}
