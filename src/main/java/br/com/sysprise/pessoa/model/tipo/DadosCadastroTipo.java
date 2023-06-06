package br.com.sysprise.pessoa.model.tipo;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTipo(@NotBlank(message = "Obrigatório o preenchimento do nome")
                                String nome) {
}
