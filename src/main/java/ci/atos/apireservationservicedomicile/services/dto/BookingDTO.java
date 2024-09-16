package ci.atos.apireservationservicedomicile.services.dto;

import ci.atos.apireservationservicedomicile.enumerations.BookingStatus;
import ci.atos.apireservationservicedomicile.models.Customer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDTO {
    private Long id;
    private LocalDateTime bookingTime;
    private BookingStatus status;
    private Customer customer;
}
