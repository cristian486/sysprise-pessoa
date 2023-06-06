package br.com.sysprise.pessoa.service.pessoa;

import br.com.sysprise.pessoa.model.pessoa.DadosListagemPessoa;
import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import br.com.sysprise.pessoa.model.pessoa.fisica.DadosListagemPessoaFisica;
import br.com.sysprise.pessoa.model.pessoa.fisica.PessoaFisica;
import br.com.sysprise.pessoa.model.pessoa.juridica.DadosListagemPessoaJuridica;
import br.com.sysprise.pessoa.model.pessoa.juridica.PessoaJuridica;
import br.com.sysprise.pessoa.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa findPessoaById(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A pessoa solicitada não foi encontrada!"));
    }

    public Page<? extends DadosListagemPessoa> listarClientes(Pageable pageable) {
        Page<DadosListagemPessoa> clientes = pessoaRepository.findAllClientes(pageable).map(pessoa -> {
            if(pessoa instanceof PessoaFisica)
                return new DadosListagemPessoaFisica((PessoaFisica) pessoa);

            return new DadosListagemPessoaJuridica((PessoaJuridica) pessoa);
        });
        return clientes;
    }

    public Page<? extends DadosListagemPessoa> listarFornecedores(Pageable pageable) {
        Page<DadosListagemPessoa> fornecedores = pessoaRepository.findAllFornecedores(pageable).map(pessoa -> {
            if(pessoa instanceof PessoaFisica)
                return new DadosListagemPessoaFisica((PessoaFisica) pessoa);

            return new DadosListagemPessoaJuridica((PessoaJuridica) pessoa);
        });
        return fornecedores;
    }

    public Boolean existById(Long id) {
        return pessoaRepository.existsById(id);
    }
}
