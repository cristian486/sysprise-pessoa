package br.com.sysprise.pessoa.model.pessoa.fisica;

import br.com.sysprise.pessoa.model.contato.DadosCadastroContato;
import br.com.sysprise.pessoa.model.endereco.DadosCadastroEndereco;
import br.com.sysprise.pessoa.model.pessoa.Genero;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

// TODO
public record DadosCadastroPessoaFisica(@NotBlank(message = "Obrigatório o preenchimento do nome")
                                        String nome,
                                        @Pattern(regexp = "\\d{11}", message = "O padrão esperado é de onze dígitos sem ponto ou traço.")
                                        @NotBlank(message = "Obrigatório o preenchimento do cpf")
                                        String cpf,
                                        @NotNull(message = "Obrigatório o envio do gênero")
                                        Genero genero,
                                        @Past(message = "A data de nascimento não pode ser presente ou futura")
                                        @NotNull(message = "Obrigatório o envio da data de nascimento")
                                        LocalDate dataDeNascimento,
                                        @Valid
                                        @NotNull(message = "Obrigatório o envio dos dados para cadastro do endereço")
                                        DadosCadastroEndereco endereco,
                                        @NotNull(message = "Obrigatório o envio dos tipos da pessoa")
                                        @NotEmpty(message = "Obrigatório ao menos um tipo para a pessoa")
                                        List<Long> tipos,
                                        @Valid
                                        @NotEmpty(message = "Obrigatório ao menos um contato para a pessoa")
                                        @NotNull(message = "Obrigatório o envio dos dados para cadastro do contato")
                                        List<DadosCadastroContato> contatos
) {
}
