package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;

    public ValidacaoTutorComLimiteDeAdocoes(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDTO dto) {
        int quantidadeDeAdocoes = adocaoRepository.countByTutorIdAndStatus(dto.tutorId(), StatusAdocao.APROVADO);
        if (quantidadeDeAdocoes == 5) {
            throw new ValidationException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }
}
