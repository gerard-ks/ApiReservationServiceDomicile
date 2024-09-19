package ci.atos.apireservationservicedomicile.services.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDTO {
    @NotBlank(message = "Le role est requis")
    private String roleName;
}
