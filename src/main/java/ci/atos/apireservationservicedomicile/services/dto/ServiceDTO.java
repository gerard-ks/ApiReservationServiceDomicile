package ci.atos.apireservationservicedomicile.services.dto;

import ci.atos.apireservationservicedomicile.models.Customer;
import ci.atos.apireservationservicedomicile.models.Provider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDTO {
    private Long id;
    private int rating;
    private String comment;
    private Provider provider;
    private Customer customer;
}
