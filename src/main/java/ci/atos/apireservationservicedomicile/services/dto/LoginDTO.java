package ci.atos.apireservationservicedomicile.services.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "Le username est requis")
    private String username;
    @NotBlank(message = "Le mot de passe est requis")
    private String password;
}
