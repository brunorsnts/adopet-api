package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;

    public ValidacaoPetComAdocaoEmAndamento(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDTO dto) {
        boolean petComAdocaoEmAndamento = adocaoRepository.existsByPetIdAndStatus(dto.petId(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if (petComAdocaoEmAndamento) {
            throw new ValidationException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
