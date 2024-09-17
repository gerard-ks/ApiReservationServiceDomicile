package ci.atos.apireservationservicedomicile.services.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingServiceRequestDTO {
    @NotNull(message = "La reservation est requise")
    private BookingRequestDTO booking;
    @NotNull(message = "Le service est requis")
    private List<Long> listServiceId;
}
