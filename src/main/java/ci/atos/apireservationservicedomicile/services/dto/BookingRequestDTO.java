package ci.atos.apireservationservicedomicile.services.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingRequestDTO {
    @NotNull(message = "La date de reservation est requis")
    private LocalDateTime bookingTime;
    @NotNull(message = "Le client est requis")
    private Long customerId;
}
