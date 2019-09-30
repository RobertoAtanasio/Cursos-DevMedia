package br.com.rapl.jsfappcdi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
//@RequestScoped
@SessionScoped
//@ApplicationScoped
public class ControlePrincipalBean implements Serializable{

	private static final long serialVersionUID = 685638259282218974L;

	private String mensagem;
	
	public ControlePrincipalBean(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss:SSS");
		LocalDateTime agora = LocalDateTime.now();
		String agoraFormatado = agora.format(formatter);
		mensagem = "Bem vindo a sua primeira aplica��o com JSF2 - " +agoraFormatado;
	}

	public String sobre(){
		mensagem = "Voc� navegou de maneira din�mica SEM redirecionar para a p�gina sobre.xhtml";
		return "sobre";
	}
	
	public String sobreRedirecionado(){
		mensagem = "Voc� foi redirecionado para a p�gina sobre.xhtml";
		return "sobre?faces-redirect=true";
	}
	
	public String geraErro(){
		// A forma de configura��o via arquivo faces-config.xml era a forma antes do JSF 2 
		// apesar do retorno ser 'erro' a p�gina a ser tratada est� definida em faces-config.xml'. Ver trecho do c�digo:
		// Se exitir apenas um tipo de p�gina para retorno do string 'erro', a tag <from-action> n�o � necess�ria.
		//		<navigation-case>
		//			<from-action>#{controlePrincipalBean.geraErro()}</from-action>
		//			<from-outcome>erro</from-outcome>
		//			<to-view-id>/errorPage.xhtml</to-view-id>
		//		</navigation-case>
		return "erro";
	}
	
	public String geraErroGrave(){
		return "erro";
	}
	
	public String geraLogin(){
		return "login";
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
