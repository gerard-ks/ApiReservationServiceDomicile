package ci.atos.apireservationservicedomicile.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingServiceDTO {
    private Long id;
    private BookingDTO booking;
    private ServiceHomeDTO serviceHome;
}
