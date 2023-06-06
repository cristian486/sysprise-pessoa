package br.com.sysprise.pessoa.model.pessoa.juridica;


import br.com.sysprise.pessoa.model.pessoa.DadosAtualizarPessoa;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class DadosAtualizarPessoaJuridica extends DadosAtualizarPessoa {
    @Min(1)
    @NotNull(message = "Obrigatório o envio do ID da pessoa jurídica")
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    @Pattern(regexp = "\\d{14}", message = "O padrão esperado é quatorze dígitos sem ponto, barra ou traço!")
    private String cnpj;
}
