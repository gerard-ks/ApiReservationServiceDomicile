package ci.atos.apireservationservicedomicile.services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceHomeRequestDTO {
    @NotBlank(message = "Le nom est requis")
    private String name;
    @NotBlank(message = "La description est requise")
    private String description;
    @NotNull(message = "Le prix est requis")
    private Double price;
    @NotNull(message = "Le prestataire est requis")
    private Long providerId;
    @NotNull(message = "La categorie est requis")
    private Long serviceCategoryId;
}
