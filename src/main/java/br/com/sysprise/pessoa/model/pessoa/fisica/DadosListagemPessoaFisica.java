package br.com.sysprise.pessoa.model.pessoa.fisica;


import br.com.sysprise.pessoa.model.pessoa.DadosListagemPessoa;
import br.com.sysprise.pessoa.model.pessoa.Genero;
import lombok.Getter;

@Getter
public final class DadosListagemPessoaFisica extends DadosListagemPessoa {
    private final Genero genero;

    public DadosListagemPessoaFisica(PessoaFisica pessoaFisica) {
        super(pessoaFisica.getId(), pessoaFisica.getNome());
        this.genero = pessoaFisica.getGenero();
    }
}
