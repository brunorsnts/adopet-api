package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.AbrigoCadastroDTO;
import br.com.alura.adopet.api.dto.abrigo.DadosAbrigoDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AbrigoService {

    private final AbrigoRepository repository;

    public AbrigoService(AbrigoRepository repository) {
        this.repository = repository;
    }

    public List<DadosAbrigoDTO> findAll() {
        List<Abrigo> abrigos = repository.findAll();
        return abrigos.stream()
                .map(DadosAbrigoDTO::new)
                .toList();
    }

    @Transactional
    public DadosAbrigoDTO cadastrar(AbrigoCadastroDTO dto) {
        validaCadastroAbrigo(dto);
        Abrigo abrigo = new Abrigo(dto);
        abrigo = repository.save(abrigo);
        return new DadosAbrigoDTO(abrigo);
    }

    private void validaCadastroAbrigo(AbrigoCadastroDTO dto) {
        boolean nomeJaCadastrado = repository.existsByNome(dto.nome());
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidationException("Dados já cadastrados para outro abrigo!");
        }
    }
}
