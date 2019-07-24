package br.com.rapl.cursos.oqueecdi.unitedburger.domain.model;

public enum Prioridade {
    ALTA("Alta"),
    MEDIA("M�dia"),
    BAIXA("Baixa");

    private String descricao;

    Prioridade(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}