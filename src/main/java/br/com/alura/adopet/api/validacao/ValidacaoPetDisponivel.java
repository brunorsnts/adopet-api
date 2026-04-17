package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;

    public ValidacaoPetDisponivel(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }

    @Override
    public void validar(SolicitacaoAdocaoDTO dto) {
        boolean petJaAdotado = adocaoRepository.existsByPetIdAndStatus(dto.petId(), StatusAdocao.APROVADO);
        if (petJaAdotado) {
            throw new ValidationException("Pet já foi adotado!");
        }
    }
}
