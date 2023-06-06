package br.com.sysprise.pessoa.repository;

import br.com.sysprise.pessoa.model.pessoa.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select p from Pessoa p join p.tipos t where upper(t.nome) = upper('CLIENTE') ")
    Page<Pessoa> findAllClientes(Pageable pageable);

    @Query("select p from Pessoa p join p.tipos t where upper(t.nome) = upper('FORNECEDOR') ")
    Page<Pessoa> findAllFornecedores(Pageable pageable);
}
