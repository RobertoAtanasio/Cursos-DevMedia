package br.com.rapl.jsfappcdi.controle;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name="controlePessoa")
@SessionScoped
public class ControlePessoa implements Serializable {

	private static final long serialVersionUID = 2130859543716132451L;

	private Boolean opcaoSelecionada;
	private Boolean pessoaFisica;
	private Integer id;
	private String nome;
	private String cpf;
	private String cnpj;
	private String saida;
	
	public ControlePessoa(){
		opcaoSelecionada = false;		
	}
	
	public void tipoChange(ValueChangeEvent e){
		pessoaFisica = (Boolean) e.getNewValue();	
		selecionar();
	}

	public String selecionar(){
		opcaoSelecionada = true;
		String selecao = pessoaFisica == true ? "Pessoa Fisica" : "Pessoa Juridica";
		FacesMessage msg = new FacesMessage("Tipo selecionado: "+selecao);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "formPessoa";
	}
	
	public String exibirDados(){
		saida = "";
		saida +="Valores recebidos:";
		saida += "<br/>"+(pessoaFisica ? "Nome: "+nome : "Razão social: "+nome);
		saida += "<br/>"+(pessoaFisica ? "CPF: "+cpf : "CNPJ: "+cnpj);
		FacesMessage msg = new FacesMessage("Dados recebidos com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);		
		return "dadosPessoa";
	}
	
	public String voltar(){
		opcaoSelecionada = false;
		id = null; 
		nome = "";
		cpf = "";
		cnpj = "";
		return "formPessoa";
	}
	
	public Boolean getOpcaoSelecionada() {
		return opcaoSelecionada;
	}

	public void setOpcaoSelecionada(Boolean opcaoSelecionada) {
		this.opcaoSelecionada = opcaoSelecionada;
	}

	public Boolean getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(Boolean pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}
	
	
}
