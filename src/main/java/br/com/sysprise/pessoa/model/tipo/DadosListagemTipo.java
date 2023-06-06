package br.com.sysprise.pessoa.model.tipo;

public record DadosListagemTipo(Long id, String nome) {

    public DadosListagemTipo(Tipo tipo) {
        this(tipo.getId(), tipo.getNome());
    }
}
