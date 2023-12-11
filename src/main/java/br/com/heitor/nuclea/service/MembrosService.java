package br.com.heitor.nuclea.service;

import br.com.heitor.nuclea.model.entities.Membros;
import br.com.heitor.nuclea.model.entities.MembrosId;
import br.com.heitor.nuclea.model.entities.Pessoa;
import br.com.heitor.nuclea.model.entities.Projeto;
import br.com.heitor.nuclea.repository.MembrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembrosService {

    private final ProjetoService projetoService;
    private final PessoaService pessoaService;
    private final MembrosRepository membrosRepository;

    public MembrosService(ProjetoService projetoService, PessoaService pessoaService, MembrosRepository membrosRepository) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
        this.membrosRepository = membrosRepository;
    }

    public List<Membros> getAllMembrosByIdprojeto(Long idprojeto) {
        return membrosRepository.findAllByIdprojeto(idprojeto);
    }

    public Membros addMembro(Long idprojeto, Long idpessoa) {
        MembrosId membrosId = new MembrosId();
        membrosId.setIdprojeto(idprojeto);
        membrosId.setIdpessoa(idpessoa);

        Membros membro = new Membros();
        membro.setIdprojeto(idprojeto);
        membro.setIdpessoa(idpessoa);

        return membrosRepository.save(membro);
    }

    public void deleteMembrosByIds(Long idprojeto, Long idpessoa) {
//        membrosRepository.deleteByIdprojetoAndIdpessoa(idprojeto, idpessoa);
    }
}