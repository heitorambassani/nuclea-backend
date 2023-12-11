package br.com.heitor.nuclea.repository;

import br.com.heitor.nuclea.model.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findAllByFuncionarioTrue();

}
