package br.com.devmedia.java.jsf.lembrete.web.model;

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
