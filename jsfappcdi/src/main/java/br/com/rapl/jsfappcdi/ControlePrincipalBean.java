package br.com.rapl.jsfappcdi;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ControlePrincipalBean implements Serializable{

	private static final long serialVersionUID = 685638259282218974L;

	private String mensagem;
	
	public ControlePrincipalBean(){

		mensagem = "Bem vindo a sua primeira aplicação com JSF2";
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
