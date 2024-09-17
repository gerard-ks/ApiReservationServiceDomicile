package ci.atos.apireservationservicedomicile.services.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {
    @NotNull(message = "La note est requise")
    private int rating;
    @NotNull(message = "Le commentaire est requis")
    private String comment;
    @NotNull(message = "Le prestataire est requis")
    private Long providerId;
    @NotNull(message = "Le client est requis")
    private Long customerId;
}
