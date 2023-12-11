package br.com.heitor.nuclea.repository;

import br.com.heitor.nuclea.model.entities.Membros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembrosRepository extends JpaRepository<Membros, Long> {
    List<Membros> findAllByIdprojeto(Long idprojeto);
    void deleteByIdprojetoAndIdpessoa(Long idprojeto, Long idpessoa);
}