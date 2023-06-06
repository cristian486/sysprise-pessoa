package br.com.sysprise.pessoa.controller;

import br.com.sysprise.pessoa.model.pessoa.DadosListagemPessoa;
import br.com.sysprise.pessoa.service.pessoa.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping("/cliente")
    public ResponseEntity<Page<? extends DadosListagemPessoa>> listarCliente(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<? extends DadosListagemPessoa> dadosListagem = pessoaService.listarClientes(pageable);
        return  ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/fornecedor")
    public ResponseEntity<Page<? extends DadosListagemPessoa>> listarFornecedor(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<? extends DadosListagemPessoa> dadosListagem = pessoaService.listarFornecedores(pageable);
        return  ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }
}
