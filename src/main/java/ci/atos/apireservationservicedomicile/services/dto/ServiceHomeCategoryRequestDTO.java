package ci.atos.apireservationservicedomicile.services.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceHomeCategoryRequestDTO {
    @NotBlank(message = "Le nom est requis")
    private String name;
    @NotBlank(message = "La description est requise")
    private String description;
}
