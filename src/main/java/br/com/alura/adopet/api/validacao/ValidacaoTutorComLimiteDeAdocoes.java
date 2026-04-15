package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;
    private final TutorRepository tutorRepository;

    public ValidacaoTutorComLimiteDeAdocoes(AdocaoRepository adocaoRepository,
                                            TutorRepository tutorRepository) {
        this.adocaoRepository = adocaoRepository;
        this.tutorRepository = tutorRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDTO dto) {
        List<Adocao> adocoes = adocaoRepository.findAll();
        Tutor tutor = tutorRepository.getReferenceById(dto.tutorId());
        for (Adocao a : adocoes) {
            int contador = 0;
            if (a.getTutor().equals(tutor) && a.getStatus().equals(StatusAdocao.APROVADO)) {
                contador = contador + 1;
            }
            if (contador == 5) {
                throw new ValidationException("Tutor chegou ao limite máximo de 5 adoções!");
            }
        }
    }
}
