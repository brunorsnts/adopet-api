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
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao{

    private final AdocaoRepository adocaoRepository;
    private final TutorRepository tutorRepository;

    public ValidacaoTutorComAdocaoEmAndamento(AdocaoRepository adocaoRepository,
                                              TutorRepository tutorRepository) {
        this.adocaoRepository = adocaoRepository;
        this.tutorRepository = tutorRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDTO dto) {
        List<Adocao> adocoes = adocaoRepository.findAll();
        Tutor tutor = tutorRepository.getReferenceById(dto.tutorId());
        for (Adocao a : adocoes) {
            if (a.getTutor().equals(tutor) && a.getStatus().equals(StatusAdocao.AGUARDANDO_AVALIACAO)) {
                throw new ValidationException("Tutor já possui outra adoção aguardando avaliação!");
            }
        }
    }
}
