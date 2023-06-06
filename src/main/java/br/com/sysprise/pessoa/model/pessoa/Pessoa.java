package br.com.sysprise.pessoa.model.pessoa;

import br.com.sysprise.pessoa.model.pessoa.fisica.DadosDetalhamentoPessoaFisica;
import br.com.sysprise.pessoa.model.pessoa.fisica.PessoaFisica;
import br.com.sysprise.pessoa.model.pessoa.juridica.DadosDetalhamentoPessoaJuridica;
import br.com.sysprise.pessoa.model.pessoa.juridica.PessoaJuridica;
import br.com.sysprise.pessoa.model.tipo.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Boolean habilitado;

    protected Long enderecoId;

    @OneToMany
    protected Set<Tipo> tipos = new HashSet<>();

    public abstract String getNome();

    public static DadosDetalhamentoPessoa converterPessoaParaDto(Pessoa pessoa) {
        if(pessoa instanceof PessoaFisica)
            return new DadosDetalhamentoPessoaFisica((PessoaFisica) pessoa);

        return new DadosDetalhamentoPessoaJuridica((PessoaJuridica) pessoa);
    }

    public Optional<Tipo> buscarTipoPorId(Long id) {
        return this.tipos.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public void removerTipo(Tipo tipo) {
        this.tipos.remove(tipo);
    }

    public void adicionarTipo(Tipo tipo) {
        this.tipos.add(tipo);
    }

    public void definirEnderecoId(Long id) {
        this.enderecoId = id;
    }
}
