package br.com.sysprise.pessoa.repository;


import br.com.sysprise.pessoa.model.tipo.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {

    @Query(value = "select count(pt.tipos_id) = 0 from tipo t inner join pessoa_tipos pt on t.id = pt.tipos_id where t.id = :id", nativeQuery = true)
    Long haPessoasVinculadasTipo(Long id);
}
