package ci.atos.apireservationservicedomicile.services.dto;

import ci.atos.apireservationservicedomicile.enumerations.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingUpdateRequestDTO {
    @NotNull(message = "Le statut est requis")
    private BookingStatus status;
    @NotNull(message = "Le client est requis")
    private Long customerId;
}
