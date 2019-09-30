package br.com.rapl.jsfappcdi.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

@ManagedBean(name="controleComponentes")
@SessionScoped
public class ControleComponentes implements Serializable {

	private static final long serialVersionUID = -2411877451092479285L;
	
	private String texto;
	private String idComponente;
	
	public ControleComponentes(){}
	
	public String geraTexto(){
		texto = "Texto gerado: < > & <b>Texto em negrito</b>";
		return "componentesParte2";
	}
	
	public String executar() {
		return "componentesParte3";
	}
	// Criar um evento listener para capturar o componente onde houve a ação
	
	public void listener (ActionEvent event) {
		UIComponent source = event.getComponent();
		idComponente = "Componenet que executar a ação: " + source.getId();
		System.out.println(">>> Componente: " + idComponente);
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(String idComponente) {
		this.idComponente = idComponente;
	}

}
