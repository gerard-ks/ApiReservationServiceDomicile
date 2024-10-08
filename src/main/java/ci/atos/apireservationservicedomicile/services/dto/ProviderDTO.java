package ci.atos.apireservationservicedomicile.services.dto;

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
}
