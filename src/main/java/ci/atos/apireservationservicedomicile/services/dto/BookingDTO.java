package ci.atos.apireservationservicedomicile.services.dto;

import ci.atos.apireservationservicedomicile.models.enumerations.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDTO {
    private Long id;
    private LocalDateTime bookingTime;
    private BookingStatus status;
    private CustomerDTO customer;
}
