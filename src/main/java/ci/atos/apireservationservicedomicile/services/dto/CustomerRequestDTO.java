package ci.atos.apireservationservicedomicile.services.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {
    @NotBlank(message = "Le nom est requis")
    private String name;
    @NotBlank(message = "Le numéro de téléphone est requis")
    private String phone;
    @Email(message = "L'email doit être valide")
    private String email;
    @NotBlank(message = "L'adresse est requis")
    private String address;
}
