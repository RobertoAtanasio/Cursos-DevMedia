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
		mensagem = "Bem vindo a sua primeira aplicação com JSF2 - " +agoraFormatado;
	}

	public String sobre(){
		mensagem = "Você navegou de maneira dinâmica SEM redirecionar para a página sobre.xhtml";
		return "sobre";
	}
	
	public String sobreRedirecionado(){
		mensagem = "Você foi redirecionado para a página sobre.xhtml";
		return "sobre?faces-redirect=true";
	}
	
	public String geraErro(){
		// A forma de configuração via arquivo faces-config.xml era a forma antes do JSF 2 
		// apesar do retorno ser 'erro' a página a ser tratada está definida em faces-config.xml'. Ver trecho do código:
		// Se exitir apenas um tipo de página para retorno do string 'erro', a tag <from-action> não é necessária.
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
