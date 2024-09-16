package ci.atos.apireservationservicedomicile.services.dto;

import ci.atos.apireservationservicedomicile.models.Booking;
import ci.atos.apireservationservicedomicile.models.Service;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationServiceDTO {
    private Long id;
    private Booking booking;
    private Service service;
}
