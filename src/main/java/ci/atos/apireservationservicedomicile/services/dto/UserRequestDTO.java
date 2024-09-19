package ci.atos.apireservationservicedomicile.services.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    @Email(message = "L'email doit etre valide")
    private String email;
    @NotBlank(message = "Le username est obligatoire")
    private String username;
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
    @NotNull(message = "Le role est obligatoire")
    private Long roleId;
}
