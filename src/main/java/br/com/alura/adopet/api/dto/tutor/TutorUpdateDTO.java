package br.com.alura.adopet.api.dto.tutor;

import br.com.alura.adopet.api.model.Tutor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TutorUpdateDTO(
        @NotBlank String nome,
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}", message = "Por favor siga o padrão (xx) xxxx-xxxx") String telefone,
        @Email String email
) {
    public TutorUpdateDTO(Tutor tutor) {
        this(tutor.getNome(), tutor.getTelefone(), tutor.getEmail());
    }
}
