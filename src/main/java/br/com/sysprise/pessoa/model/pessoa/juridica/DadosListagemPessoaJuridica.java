package br.com.sysprise.pessoa.model.pessoa.juridica;


import br.com.sysprise.pessoa.model.pessoa.DadosListagemPessoa;
import lombok.Getter;

@Getter
public final class DadosListagemPessoaJuridica extends DadosListagemPessoa {
    private String razaoSocial;

    public DadosListagemPessoaJuridica(PessoaJuridica pessoaJuridica) {
        super(pessoaJuridica.getId(), pessoaJuridica.getNome());
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
    }
}
