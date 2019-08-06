package br.com.rapl.cursos.oqueecdi.unitedburger.domain.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Contato;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Notificacao;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.servico.ContatoService;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.servico.NotificacaoService;

// Obs.: para a utilização da CDI são feitas alterações no uso das anotações;
//       O @ManagedBean é substituído pela @Named
//       A biblioteca import do @RequestScoped é substituída da JSF pela da CDI:
//			JSF: import javax.faces.bean.RequestScoped;
//			CDI: import javax.enterprise.context.RequestScoped;

// Obs.: como as classe Contato, ContatoService, Notificacao e NotificacaoService têm o construtor simples, podemos injetar as mesmas.

//@ManagedBean
@Named
@RequestScoped		// @RequestScoped = Escopo da requisição: só existe durante a requisição 
public class ContatoBean {

	@Inject
	private Contato contato;
	
	@Inject
	private ContatoService contatoService;

	@Inject
	private Notificacao notificacao;
	
	@Inject
	private NotificacaoService notificacaoService;

	private boolean receberNotificacao;
	
	// O @PostConstruct será executado somente após a injeção de dependências acima.
	@PostConstruct
	public void init() {
		receberNotificacao = false;
	}

	// Este método deixará disponível para o html o objeto contato, uma vez que o
	// objeto foi definido como private (ver definição acima).
	public Contato getContato() {
		return contato;
	}

	public boolean getReceberNotificacao() {
		return receberNotificacao;
	}

	public void setReceberNotificacao(boolean receberNotificacao) {
		this.receberNotificacao = receberNotificacao;
	}

	// Esta assinatura do método é padrão. Recebe um ActionEvent.
	// É preciso a definição desse evento para que o JSF funcione com o Ajax

	public void cadastrar(ActionEvent event) {
		FacesMessage message;

		try {
			contatoService.cadastrar(contato);

			if (receberNotificacao) {
				notificacao.setEmail(contato.getEmail());
				notificacaoService.cadastrar(notificacao);
			}

			contato = null;

			message = new FacesMessage("E-mail cadastrado com sucesso!");
		} catch (RuntimeException e) {
			message = new FacesMessage("Serviço temporariamente indisponível");
		}

		// Avisa ao JSF que existe uma mensagem a ser exibida na view.
		// O primeiro parâmetro é um título para a mensagem.
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}



//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ActionEvent;
//import javax.inject.Named;
//
//import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Contato;
//import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Notificacao;
//import br.com.rapl.cursos.oqueecdi.unitedburger.domain.servico.ContatoService;
//import br.com.rapl.cursos.oqueecdi.unitedburger.domain.servico.NotificacaoService;
//
//@ManagedBean
//@RequestScoped		// @RequestScoped = Escopo da requisição: só existe durante a requisição 
//public class ContatoBean {
//
//	private Contato contato = new Contato();
//	private ContatoService contatoService = new ContatoService();
//
//	private Notificacao notificacao = new Notificacao();
//	private NotificacaoService notificacaoService = new NotificacaoService();
//
//	private boolean receberNotificacao;
//
//	// Este método deixará disponível para o html o objeto contato, uma vez que o
//	// objeto foi definido como private (ver definição acima).
//	public Contato getContato() {
//		return contato;
//	}
//
//	public boolean getReceberNotificacao() {
//		return receberNotificacao;
//	}
//
//	public void setReceberNotificacao(boolean receberNotificacao) {
//		this.receberNotificacao = receberNotificacao;
//	}
//
//	// Esta assinatura do método é padrão. Recebe um ActionEvent.
//	// É preciso a definição desse evento para que o JSF funcione com o Ajax
//
//	public void cadastrar(ActionEvent event) {
//		FacesMessage message;
//
//		try {
//			contatoService.cadastrar(contato);
//
//			if (receberNotificacao) {
//				notificacao.setEmail(contato.getEmail());
//				notificacaoService.cadastrar(notificacao);
//			}
//
//			contato = null;
//
//			message = new FacesMessage("E-mail cadastrado com sucesso!");
//		} catch (RuntimeException e) {
//			message = new FacesMessage("Serviço temporariamente indisponível");
//		}
//
//		// Avisa ao JSF que existe uma mensagem a ser exibida na view.
//		// O primeiro parâmetro é um título para a mensagem.
//		FacesContext.getCurrentInstance().addMessage(null, message);
//	}
//}
