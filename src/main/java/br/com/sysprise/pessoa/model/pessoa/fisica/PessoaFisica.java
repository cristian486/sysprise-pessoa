package br.com.sysprise.pessoa.model.pessoa.fisica;

import br.com.sysprise.pessoa.model.pessoa.Genero;
import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import br.com.sysprise.pessoa.model.tipo.Tipo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf", callSuper = true)
public class PessoaFisica extends Pessoa {

    private String nome;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private LocalDate dataDeNascimento;

    public PessoaFisica(DadosCadastroPessoaFisica dadosCadastro, List<Tipo> tipos) {
        this.nome = dadosCadastro.nome();
        this.cpf = dadosCadastro.cpf();
        this.genero = dadosCadastro.genero();
        this.dataDeNascimento = dadosCadastro.dataDeNascimento();
        this.habilitado = Boolean.TRUE;
        this.tipos = new HashSet<>(tipos);
    }

    public void atualizarCadastro(DadosAtualizarPessoaFisica dadosAtualizar) {
        if(dadosAtualizar.getNome() != null && !dadosAtualizar.getNome().isEmpty())
            this.nome = dadosAtualizar.getNome();

        if(dadosAtualizar.getCpf() != null && !dadosAtualizar.getCpf().isEmpty())
            this.cpf = dadosAtualizar.getCpf();

        if(dadosAtualizar.getGenero() != null && !this.genero.equals(dadosAtualizar.getGenero()))
            this.genero = dadosAtualizar.getGenero();

        if(dadosAtualizar.getDataDeNascimento() != null && !this.dataDeNascimento.equals(dadosAtualizar.getDataDeNascimento()))
            this.dataDeNascimento = dadosAtualizar.getDataDeNascimento();
    }
}
