package br.com.sysprise.pessoa.model.pessoa.fisica;

import br.com.sysprise.pessoa.model.contato.DadosListagemContato;
import br.com.sysprise.pessoa.model.endereco.DadosDetalhamentoEndereco;
import br.com.sysprise.pessoa.model.pessoa.DadosDetalhamentoPessoa;
import br.com.sysprise.pessoa.model.pessoa.Genero;
import br.com.sysprise.pessoa.model.tipo.DadosDetalhamentoTipo;
import lombok.Getter;
import pb.Endereco;
import pb.ListaContatos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.stream.Collectors;

@Getter
public final class DadosDetalhamentoPessoaFisica extends DadosDetalhamentoPessoa {
    private Long id;
    private String nome;
    private String cpf;
    private Genero genero;
    private LocalDate dataDeNascimento;


    public DadosDetalhamentoPessoaFisica(PessoaFisica pessoaFisica) {
        super();
        this.id = pessoaFisica.getId();
        this.nome = pessoaFisica.getNome();
        this.cpf = pessoaFisica.getCpf();
        this.genero = pessoaFisica.getGenero();
        this.dataDeNascimento = pessoaFisica.getDataDeNascimento();
    }

    public DadosDetalhamentoPessoaFisica(PessoaFisica pessoaFisica, Endereco endereco, ListaContatos listaContatos) {
        super(new DadosDetalhamentoEndereco(endereco),
                pessoaFisica.getTipos().stream().map(DadosDetalhamentoTipo::new).toList(),
                listaContatos.getContatoList().stream().map(DadosListagemContato::new).toList());
        this.id = pessoaFisica.getId();
        this.nome = pessoaFisica.getNome();
        this.cpf = pessoaFisica.getCpf();
        this.genero = pessoaFisica.getGenero();
        this.dataDeNascimento = pessoaFisica.getDataDeNascimento();
    }
}
