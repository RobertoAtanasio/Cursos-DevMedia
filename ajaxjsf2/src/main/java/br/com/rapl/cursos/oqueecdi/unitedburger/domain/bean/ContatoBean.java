package br.com.rapl.cursos.oqueecdi.unitedburger.domain.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Contato;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.model.Notificacao;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.servico.ContatoService;
import br.com.rapl.cursos.oqueecdi.unitedburger.domain.servico.NotificacaoService;

@ManagedBean
@RequestScoped
public class ContatoBean {

	private Contato contato = new Contato();
	private ContatoService contatoService = new ContatoService();

	private Notificacao notificacao = new Notificacao();
	private NotificacaoService notificacaoService = new NotificacaoService();

	private boolean receberNotificacao;

	// Este m�todo deixar� dispon�vel para o html o objeto contato, uma vez que o
	// objeto � definido como private
	public Contato getContato() {
		return contato;
	}

	public boolean getReceberNotificacao() {
		return receberNotificacao;
	}

	public void setReceberNotificacao(boolean receberNotificacao) {
		this.receberNotificacao = receberNotificacao;
	}

	// Esta assinatura do m�todo � padr�o. Recebe um ActionEvent.
	// � preciso a defini��o desse evento para que o JSF funcione com o Ajax

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
		// O primeiro par�metro um um t�tulo para a mensagem.
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
