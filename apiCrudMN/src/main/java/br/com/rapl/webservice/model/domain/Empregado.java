package br.com.rapl.webservice.model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 1. (mappedBy = "empregados") contém o mesmo nome da lista de empregados da classe Projeto.java
//    O atributo "empregados" é o dono do relacionamento.
// 2. A entidade Projeto representa o lado forte da relação. Logo a tabela de relacionamento
//    terá o nome projeto_empregado
// 3. O @JsonIgnore é para evitar que a lista de projetos seja retornada no JSon quando a lista de
//    empregados for selecionada.

@Entity
public class Empregado {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cargo;
    
    @ManyToMany(mappedBy = "empregados")
    @JsonIgnore
    private List<Projeto> projetos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
}
