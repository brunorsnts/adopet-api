package br.com.alura.adopet.api.dto.abrigo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AbrigoCadastroDTO(
        @NotBlank(message = "Campo obrigatório") String nome,
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}") @NotBlank String telefone,
        @NotBlank(message = "Campo obrigatório") @Email String email
) {
}
