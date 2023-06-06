package br.com.sysprise.pessoa.model.pessoa.juridica;

import br.com.sysprise.pessoa.model.contato.DadosListagemContato;
import br.com.sysprise.pessoa.model.endereco.DadosDetalhamentoEndereco;
import br.com.sysprise.pessoa.model.pessoa.DadosDetalhamentoPessoa;
import br.com.sysprise.pessoa.model.tipo.DadosDetalhamentoTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pb.Endereco;
import pb.ListaContatos;

@Getter
@AllArgsConstructor
public final class DadosDetalhamentoPessoaJuridica extends DadosDetalhamentoPessoa {
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;

    public DadosDetalhamentoPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.id = pessoaJuridica.getId();
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
        this.nomeFantasia = pessoaJuridica.getNomeFantasia();
        this.cnpj = pessoaJuridica.getCnpj();
    }

    public DadosDetalhamentoPessoaJuridica(PessoaJuridica pessoaJuridica, ListaContatos listaContatos, Endereco endereco) {
        super(new DadosDetalhamentoEndereco(endereco),
                pessoaJuridica.getTipos().stream().map(DadosDetalhamentoTipo::new).toList(),
                listaContatos.getContatoList().stream().map(DadosListagemContato::new).toList());
        this.id = pessoaJuridica.getId();
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
        this.nomeFantasia = pessoaJuridica.getNomeFantasia();
        this.cnpj = pessoaJuridica.getCnpj();
    }
}
