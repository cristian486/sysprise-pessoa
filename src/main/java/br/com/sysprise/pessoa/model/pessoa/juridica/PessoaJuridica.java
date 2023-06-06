package br.com.sysprise.pessoa.model.pessoa.juridica;

import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import br.com.sysprise.pessoa.model.tipo.Tipo;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cnpj", callSuper = true)
public class PessoaJuridica extends Pessoa {

    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;

    public PessoaJuridica(DadosCadastroPessoaJuridica dadosCadastro, List<Tipo> tipos) {
        this.razaoSocial = dadosCadastro.razaoSocial();
        this.nomeFantasia = dadosCadastro.nomeFantasia();
        this.cnpj = dadosCadastro.cnpj();
        this.habilitado = Boolean.TRUE;
        this.tipos = new HashSet<>(tipos);
    }

    public void atualizarCadastro(DadosAtualizarPessoaJuridica dadosAtualizar) {
        if(dadosAtualizar.getRazaoSocial() != null && !dadosAtualizar.getRazaoSocial().isEmpty())
            this.razaoSocial = dadosAtualizar.getRazaoSocial();

        if(dadosAtualizar.getNomeFantasia() != null && !dadosAtualizar.getNomeFantasia().isEmpty())
            this.nomeFantasia = dadosAtualizar.getNomeFantasia();

        if(dadosAtualizar.getCnpj() != null && !dadosAtualizar.getCnpj().isEmpty())
            this.cnpj = dadosAtualizar.getCnpj();
   }

    @Override
    public String getNome() {
        return this.nomeFantasia;
    }
}
