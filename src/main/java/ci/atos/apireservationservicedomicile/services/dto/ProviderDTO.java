package ci.atos.apireservationservicedomicile.services.dto;

import ci.atos.apireservationservicedomicile.enumerations.ServiceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private ServiceType serviceType;
}
