package br.com.sysprise.pessoa.model.pessoa;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosAtualizarPessoa {
    @Valid
    private Optional<pb.Endereco> endereco;
    @Valid
    private Optional<List<Long>> tipos;
    @Valid
    private Optional<List<pb.Contato>> contatos;
}
