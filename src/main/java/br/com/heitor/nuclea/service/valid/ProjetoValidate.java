package br.com.heitor.nuclea.service.valid;

import br.com.heitor.nuclea.model.entities.Projeto;
import org.springframework.stereotype.Component;

@Component
public class ProjetoValidate {
    public void validate(Projeto projeto) {
//        this.validRequired(patient);
        this.validStatus(projeto);
    }

    private void validStatus(Projeto projeto) {
        System.out.println(projeto.getStatus());
    }
}
