package br.com.sysprise.pessoa.model.tipo;

public record DadosDetalhamentoTipo(Long id, String nome) {

    public DadosDetalhamentoTipo(Tipo tipo) {
        this(tipo.getId(), tipo.getNome());
    }
}
