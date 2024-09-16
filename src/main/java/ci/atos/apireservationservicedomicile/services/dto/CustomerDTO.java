package ci.atos.apireservationservicedomicile.services.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
