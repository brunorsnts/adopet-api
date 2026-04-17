package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;

    public ValidacaoTutorComAdocaoEmAndamento(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDTO dto) {
        boolean tutorTemAdocaoEmAndamento = adocaoRepository.existsByTutorIdAndStatus(dto.tutorId(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if (tutorTemAdocaoEmAndamento) {
            throw new ValidationException("Tutor já possui outra adoção aguardando avaliação!");
        }
    }
}
